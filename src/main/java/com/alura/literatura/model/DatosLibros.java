package com.alura.literatura.model;

import java.util.List;

public class DatosLibros {
    private String titulo;
    private List<DatosAutores> autores;
    private List<String> idiomas;
    private int numeroDeDescargas;

    // Constructor
    public DatosLibros() {
    }

    public DatosLibros(String titulo, List<DatosAutores> autores, List<String> idiomas, int numeroDeDescargas) {
        this.titulo = titulo;
        this.autores = autores;
        this.idiomas = idiomas;
        this.numeroDeDescargas = numeroDeDescargas;
    }

// Getters y Setters

    public String getTitulo() {return titulo; }
    public void setTitulo(String titulo) {this.titulo = titulo; }
    public List<DatosAutores> getAutores() {return autores;  }
    public void setAutores(List<DatosAutores> autores) {this.autores = autores;}
    public List<String> getIdiomas() {return idiomas;}
    public void setIdiomas(List<String> idiomas) { this.idiomas = idiomas; }
    public int getNumeroDeDescargas() {return numeroDeDescargas;}
    public void setNumeroDeDescargas(int numeroDeDescargas) {this.numeroDeDescargas = numeroDeDescargas; }
}
