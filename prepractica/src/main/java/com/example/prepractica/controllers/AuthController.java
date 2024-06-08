package com.example.prepractica.controllers;

import com.example.prepractica.domain.dtos.GeneralResponse;
import com.example.prepractica.domain.dtos.Token.TokenDTO;
import com.example.prepractica.domain.dtos.User.LoginDTO;
import com.example.prepractica.domain.dtos.User.RegisterDTO;
import com.example.prepractica.domain.entities.Token;
import com.example.prepractica.domain.entities.User;
import com.example.prepractica.repositories.TokenRepository;
import com.example.prepractica.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register (@RequestBody @Valid RegisterDTO info){

        User user = userService.findUserByIdentifier(info.getEmail());

        if (user != null) {
            return GeneralResponse.getResponse(HttpStatus.CONFLICT, "User already exist");
        }

        Date fechaNacMapped = userService.validDate(info.getFechaNac());

        if (fechaNacMapped == null) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Invalid date format that must be yyyy-MM-dd");
        }

        userService.registerUser(info, fechaNacMapped);

        return GeneralResponse.getResponse(HttpStatus.OK, "Register successful");
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid LoginDTO info) {

        User user = userService.findUserByIdentifier(info.getEmail());

        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        if (userService.correctPassword(user, info.getPassword())) {
            return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        Token token = userService.registerToken(user);

        return GeneralResponse.getResponse(HttpStatus.OK, "Login successful", new TokenDTO(token));
    }

}
