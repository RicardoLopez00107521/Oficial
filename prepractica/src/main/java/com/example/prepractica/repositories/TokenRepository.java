package com.example.prepractica.repositories;

import com.example.prepractica.domain.entities.Token;
import com.example.prepractica.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    List<Token> findByUserAndActive(User user, Boolean active); // Buscamos los tokens activos por usuario
}
