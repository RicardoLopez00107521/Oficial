package com.example.prepractica.domain.dtos.Especialidad;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEspecialidadDTO {

    @NotBlank
    private String especialidad;
}
