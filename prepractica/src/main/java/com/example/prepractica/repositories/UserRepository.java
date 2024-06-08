package com.example.prepractica.repositories;

import com.example.prepractica.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUserId(UUID userId);
}
