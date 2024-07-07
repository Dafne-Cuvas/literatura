package com.alura.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "idioma")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Idioma() {}

    public Idioma(String nombre) {
        this.nombre = nombre;
    }

    //Getter and setter

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre; }
}
