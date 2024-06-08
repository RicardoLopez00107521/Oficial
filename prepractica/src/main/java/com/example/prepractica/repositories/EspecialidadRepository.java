package com.example.prepractica.repositories;

import com.example.prepractica.domain.entities.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}
