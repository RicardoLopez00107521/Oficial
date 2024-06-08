package com.example.prepractica.repositories;

import com.example.prepractica.domain.entities.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HistorialRepository extends JpaRepository<Historial, UUID> {
}
