package com.alura.literatura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libros{
    @Id
    private Long Id;
    private String titulo;
    private Idioma idioma;
    private Integer descargas;
    @ManyToOne
    private Autores autores;

    public Libros(){}

    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", idioma=" + idioma +
                ", descargas=" + descargas +
                ", autor=" + autores +
                '}';
    }

    //Getter and setter
    public Long getId() {return Id;}
    public void setId(Long id) { Id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Idioma getIdioma() { return idioma;}
    public void setIdioma(Idioma idioma) { this.idioma = idioma;}
    public Integer getDescargas() { return descargas; }
    public void setDescargas(Integer descargas) { this.descargas = descargas; }
    public Autores getAutores() { return autores; }
    public void setAutores(Autores autores) {this.autores = autores; }
}