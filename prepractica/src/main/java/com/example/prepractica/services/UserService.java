package com.example.prepractica.services;

import com.example.prepractica.domain.dtos.User.RegisterDTO;
import com.example.prepractica.domain.entities.Rol;
import com.example.prepractica.domain.entities.Token;
import com.example.prepractica.domain.entities.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface UserService{
    User findUserByUsernameOrEmail(String username, String email);
    User findUserByIdentifier(String identifier);
    User findByUUID(UUID uuid);

    boolean correctPassword(User user, String password);

    void registerUser(RegisterDTO info, Date fechaNacMapped);

    Date validDate (String fechaNac);

    List<User> getAllUsers();

    // Security

    // Token Management
    void cleanTokens(User user);
    Token registerToken(User user);
    Boolean validateToken(User user, String token);
    public void cleanPreviousTokens(User user);

    User findUserAuthenticated();

    void createDefaultUser(String username, String userEmail, String password);
}
