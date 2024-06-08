package com.example.prepractica.domain.dtos.Prescripcion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreatePrescripcionDTO {

    @NotBlank
    private String medicamento;

    @NotBlank
    private String dosis;

    @NotNull
    private UUID fk_citaMedica;
}
