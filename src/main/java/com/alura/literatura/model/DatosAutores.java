package com.alura.literatura.model;

import jakarta.persistence.*;

public class DatosAutores {
    private String nombre;
    private String fechaNacimiento;
    private String fechaFallecimiento;

    // Constructor

    public DatosAutores() {
    }

    public DatosAutores(String nombre, String fechaNacimiento, String fechaFallecimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }
    // Getters y Setters

    public String getNombre() {return nombre; }
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getFechaNacimiento() {return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;  }
    public String getFechaFallecimiento() {return fechaFallecimiento; }
    public void setFechaFallecimiento(String fechaFallecimiento) {this.fechaFallecimiento = fechaFallecimiento; }
}
