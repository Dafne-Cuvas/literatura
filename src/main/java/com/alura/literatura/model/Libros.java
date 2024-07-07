package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int numeroDeDescargas;

    @ManyToOne
    @JoinColumn(name = "idioma_id")
    private Idioma idioma;

    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )

    private List<Autores> autores;

    public Libros() {}

    public Libros(DatosLibros datos) {
        this.titulo = datos.getTitulo();
        this.numeroDeDescargas = datos.getNumeroDeDescargas();
    }

    // Getters y Setters

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitulo() {return titulo;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public int getNumeroDeDescargas() {return numeroDeDescargas;}
    public void setNumeroDeDescargas(int numeroDeDescargas) {this.numeroDeDescargas = numeroDeDescargas;}
    public Idioma getIdioma() {return idioma;}
    public void setIdioma(Idioma idioma) { this.idioma = idioma; }
    public List<Autores> getAutores() {return autores;}
    public void setAutores(List<Autores> autores) {this.autores = autores;}
}
