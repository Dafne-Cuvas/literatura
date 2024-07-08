package com.alura.literatura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")

public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros;

    public Autores() {
    }

    public Autores(DatosAutores datosAutor){
        this.nombre = datosAutor.nombre();
        this.nacimiento = datosAutor.nacimiento();
        this.fallecimiento = datosAutor.fallecimiento();
    }

    @Override
    public String toString() {
        return "Autor{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", fallecimiento=" + fallecimiento +
                ", libros=" + libros +
                '}';
    }

    public Long getId() {return Id; }
    public void setId(Long id) {Id = id;}
    public String getNombre() {return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;}
    public Integer getNacimiento() {return nacimiento;  }
    public void setNacimiento(Integer nacimiento) { this.nacimiento = nacimiento;}
    public Integer getFallecimiento() { return fallecimiento;}
    public void setFallecimiento(Integer fallecimiento) { this.fallecimiento = fallecimiento; }
    public List<Libros> getLibros() {return libros;  }
    public void setLibros(List<Libros> libros) { this.libros = libros; }
}