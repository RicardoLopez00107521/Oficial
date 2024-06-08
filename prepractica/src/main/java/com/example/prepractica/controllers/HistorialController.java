package com.example.prepractica.controllers;

import com.example.prepractica.domain.dtos.GeneralResponse;
import com.example.prepractica.domain.dtos.Historial.CreateHistorialDTO;
import com.example.prepractica.domain.entities.Historial;
import com.example.prepractica.domain.entities.User;
import com.example.prepractica.repositories.UserRepository;
import com.example.prepractica.services.HistorialService;
import com.example.prepractica.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createHistorial (@RequestBody @Valid CreateHistorialDTO info){

        User user = userService.findByUUID(info.getFk_user());

        if (user != null) {
            historialService.CreateHistorial(user, info);
        }
        else {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Invalid FK user");
        }

        return GeneralResponse.getResponse(HttpStatus.OK, "Create Historial successful");
    }
}
