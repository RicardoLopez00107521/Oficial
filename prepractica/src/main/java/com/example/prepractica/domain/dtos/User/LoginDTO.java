package com.example.prepractica.domain.dtos.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String email; // Puede ser username o email

    @NotBlank
    private String password;
}
