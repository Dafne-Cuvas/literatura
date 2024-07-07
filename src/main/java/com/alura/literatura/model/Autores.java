package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String fechaNacimiento;
    private String fechaFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private List<Libros> libros;

    public Autores() {}

    public Autores(String nombre, String fechaNacimiento, String fechaFallecimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    //Getter and setter


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getFechaNacimiento() {return fechaNacimiento;}
    public void setFechaNacimiento(String fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}
    public String getFechaFallecimiento() {return fechaFallecimiento;}
    public void setFechaFallecimiento(String fechaFallecimiento) {this.fechaFallecimiento = fechaFallecimiento;}
    public List<Libros> getLibros() {return libros;}
    public void setLibros(List<Libros> libros) {this.libros = libros; }
}
