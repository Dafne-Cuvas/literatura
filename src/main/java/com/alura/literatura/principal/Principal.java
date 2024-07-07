package com.alura.literatura.principal;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.AutoresRepository;
import com.alura.literatura.repository.LibrosRepository;
import com.alura.literatura.service.ConsumoApi;
import com.alura.literatura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import com.alura.literatura.repository.IdiomaRepository;
import org.springframework.stereotype.Component;


import java.time.Year;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final String MENU_TEXT = """
            --------------------------------
                BIENVENIDO A LiterAlura
            --------------------------------
             MENU PRINCIPAL
            --------------------------------
            1 - Buscar Libro por Título
            2 - Buscar Autor por Nombre
            3 - Listar Libros Registrados
            4 - Listar Autores Registrados
            5 - Listar Autores Vivos
            6 - Listar Libros por Idioma
            0 - SALIR DEL PROGRAMA
            --------------------------------
            Seleccione una opción:""";

    private final Scanner teclado;
    private final ConsumoApi consumoApi;
    private final ConvierteDatos convierteDatos;
    private final AutoresRepository autoresRepository;
    private final LibrosRepository librosRepository;

    @Autowired
    public Principal(AutoresRepository autoresRepository, LibrosRepository librosRepository) {
        this.autoresRepository = autoresRepository;
        this.librosRepository = librosRepository;
        this.teclado = new Scanner(System.in);
        this.consumoApi = new ConsumoApi();
        this.convierteDatos = new ConvierteDatos();
    }

    public void muestraElMenu() {
        while (true) {
            System.out.println(MENU_TEXT);
            int opcion = leerOpcion();
            if (opcion == 0) {
                System.out.println("Gracias, la aplicación se está cerrando");
                break;
            }
            ejecutarOpcion(opcion);
        }
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
            return -1;
        }
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> buscarLibroPorTitulo();
            case 2 -> buscarAutorPorNombre();
            case 3 -> listarLibrosRegistrados();
            case 4 -> listarAutoresRegistrados();
            case 5 -> listarAutoresVivos();
            case 6 -> listarLibrosPorIdioma();
            default -> System.out.println("Seleccione una opción válida");
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Introduzca el nombre del libro que desea buscar:");
        String nombre = teclado.nextLine();
        String json = consumoApi.obtenerDatosDeApi(URL_BASE + "?search=" + nombre.replace(" ", "+").toLowerCase());

        if (json.isEmpty() || json.contains("\"count\":0")) {
            System.out.println("Libro no encontrado!");
            return;
        }

        DatosLibros datosLibros = convierteDatos.obtenerDatos(json, DatosLibros.class);
        if (datosLibros != null) {
            guardarLibroEnBD(datosLibros);
            mostrarDatosLibro(datosLibros);
        } else {
            System.out.println("Libro no encontrado!");
        }
    }
    @Autowired
    private IdiomaRepository idiomaRepository;

    private void guardarLibroEnBD(DatosLibros datosLibros) {
        Libros libro = new com.alura.literatura.model.Libros();
        libro.setTitulo(datosLibros.getTitulo());
        libro.setNumeroDeDescargas(datosLibros.getNumeroDeDescargas());

        // Obtener el idioma del primer idioma de la lista (si existe)
        String idiomaNombre = null;
        if (!datosLibros.getIdiomas().isEmpty()) {
            idiomaNombre = datosLibros.getIdiomas().get(0);
        }

        // Verificar si el idioma ya existe en la base de datos
        Idioma idioma = null;
        if (idiomaNombre != null) {
            idioma = idiomaRepository.findByNombre(idiomaNombre);
        }

        // Si no existe el idioma, crear uno nuevo
        if (idioma == null) {
            idioma = new Idioma(idiomaNombre);
            idiomaRepository.save(idioma);
        }

        // Asignar el idioma al libro
        libro.setIdioma(idioma);

        // Guardar el libro en la base de datos
        librosRepository.save(libro);
    }

    private void mostrarDatosLibro(DatosLibros datosLibros) {
        System.out.println("""
                ------------- LIBRO 📚  --------------
                Título: %s
                Autores: %s
                Idiomas: %s
                Número de descargas: %d
                --------------------------------------
                """.formatted(
                datosLibros.getTitulo(),
                obtenerNombresAutores(datosLibros.getAutores()),
                datosLibros.getIdiomas(),
                datosLibros.getNumeroDeDescargas()
        ));
    }

    private String obtenerNombresAutores(List<DatosAutores> autores) {
        return autores.stream()
                .map(DatosAutores::getNombre)
                .collect(Collectors.joining(", "));
    }

    private void buscarAutorPorNombre() {
        System.out.println("Introduzca el nombre del autor que desea buscar:");
        String nombre = teclado.nextLine();
        String json = consumoApi.obtenerDatosDeApi(URL_BASE + "?search=" + nombre.replace(" ", "+").toLowerCase());

        if (json.isEmpty() || json.contains("\"count\":0")) {
            System.out.println("Autor no encontrado!");
            return;
        }

        DatosAutores datosAutor = convierteDatos.obtenerDatos(json, DatosAutores.class);
        if (datosAutor != null) {
            guardarAutorEnBD(datosAutor);
            mostrarDatosAutor(datosAutor);
        } else {
            System.out.println("Autor no encontrado!");
        }
    }

    private void guardarAutorEnBD(DatosAutores datosAutor) {
        Autores autor = new Autores();
        autor.setNombre(datosAutor.getNombre());
        autor.setFechaNacimiento(datosAutor.getFechaNacimiento());
        autor.setFechaFallecimiento(datosAutor.getFechaFallecimiento());
        autoresRepository.save(autor);
    }

    private void mostrarDatosAutor(DatosAutores datosAutor) {
        System.out.println("""
                ------------- AUTOR 📚  --------------
                Nombre: %s
                Fecha de nacimiento: %s
                Fecha de fallecimiento: %s
                --------------------------------------
                """.formatted(
                datosAutor.getNombre(),
                datosAutor.getFechaNacimiento(),
                datosAutor.getFechaFallecimiento()
        ));
    }

    private void listarLibrosRegistrados() {
        System.out.println("--------------------------------");
        System.out.println("   LISTA DE LIBROS REGISTRADOS");
        System.out.println("--------------------------------");
        librosRepository.findAll().forEach(libro ->
                System.out.println("Título: " + libro.getTitulo() + ", Descargas: " + libro.getNumeroDeDescargas()));
    }

    private void listarAutoresRegistrados() {
        System.out.println("--------------------------------");
        System.out.println("   LISTA DE AUTORES REGISTRADOS");
        System.out.println("--------------------------------");
        autoresRepository.findAll().forEach(autor ->
                System.out.println("Nombre: " + autor.getNombre()));
    }

    private void listarAutoresVivos() {
        System.out.println("--------------------------------");
        System.out.println("   LISTA DE AUTORES VIVOS");
        System.out.println("--------------------------------");
        int anioActual = Year.now().getValue();
        autoresRepository.buscarAutoresPorDeterminadoAño(anioActual).forEach(autor ->
                System.out.println("Nombre: " + autor.getNombre()));
    }

    private void listarLibrosPorIdioma() {
        System.out.println("--------------------------------");
        System.out.println("   LISTA DE LIBROS POR IDIOMA");
        System.out.println("--------------------------------");
        System.out.println("Introduzca el idioma:");
        String idioma = teclado.nextLine();
        librosRepository.buscarLibrosPorIdioma(idioma).forEach(libro ->
                System.out.println("Título: " + libro.getTitulo() + ", Descargas: " + libro.getNumeroDeDescargas()));
    }
}
