package com.example.prepractica.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "historial_table")
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID historialId;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;
}
