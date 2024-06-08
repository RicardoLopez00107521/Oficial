package com.example.prepractica.controllers;

import com.example.prepractica.domain.dtos.GeneralResponse;
import com.example.prepractica.domain.dtos.Rol.CreateRolDTO;
import com.example.prepractica.services.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createCitaMedica (@RequestBody @Valid CreateRolDTO info){

        rolService.createRol(info);

        return GeneralResponse.getResponse(HttpStatus.OK, "Create rol successful");
    }
}
