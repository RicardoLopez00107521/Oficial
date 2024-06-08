package com.example.prepractica.domain.dtos.Historial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateHistorialDTO {

    @NotBlank
    private String descripcion;

    @NotNull
    private UUID fk_user;
}
