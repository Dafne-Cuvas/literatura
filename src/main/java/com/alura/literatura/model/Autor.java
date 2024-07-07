package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;

    @OneToOne(mappedBy = "autor")
    private DatosAutor datosAutor;


    //Getter and Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public DatosAutor getDatosAutor() {
        return datosAutor;
    }

    public void setDatosAutor(DatosAutor datosAutor) {
        this.datosAutor = datosAutor;
    }
}
