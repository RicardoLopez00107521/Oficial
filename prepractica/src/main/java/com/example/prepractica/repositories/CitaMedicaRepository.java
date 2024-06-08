package com.example.prepractica.repositories;

import com.example.prepractica.domain.entities.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, UUID> {
}
