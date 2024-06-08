package com.example.prepractica.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "prescripcion_table")
public class Prescripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID prescripcionId;

    private String medicamento;
    private String dosis;

    @ManyToOne
    @JoinColumn(name = "fk_citaMedica")
    private CitaMedica citaMedica;
}
