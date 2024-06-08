package com.example.prepractica.domain.dtos.Rol;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRolDTO {

    @NotBlank
    private String rolId;

    @NotBlank
    private String rol;
}
