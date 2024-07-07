package com.alura.literatura.repository;

import com.alura.literatura.model.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutoresRepository extends JpaRepository<Autores,Long> {
    Optional<Autores> findByNombreAndFechaNacimientoAndFechaFallecimiento(String nombre, String fechaNacimiento, String fechaFallecimiento);

    List<Autores> buscarAutoresPorDeterminadoAño(int fecha);

}
