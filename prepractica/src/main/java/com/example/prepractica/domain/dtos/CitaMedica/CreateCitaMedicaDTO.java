package com.example.prepractica.domain.dtos.CitaMedica;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCitaMedicaDTO {
    @NotBlank
    private String fechaHoraInicio;

    private String fechaHoraFin;

    @NotBlank
    private String descripcion;
}
