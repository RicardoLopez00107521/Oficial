package com.example.prepractica.domain.dtos.Token;

import com.example.prepractica.domain.entities.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {
    private String token; // Esto es lo que recibira el Token

    public TokenDTO(Token token) {
        this.token = token.getContent(); // Asigna el token para el usuario
    }
}
