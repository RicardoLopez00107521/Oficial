package com.example.prepractica.controllers;

import com.example.prepractica.domain.dtos.CitaMedica.CreateCitaMedicaDTO;
import com.example.prepractica.domain.dtos.Especialidad.CreateEspecialidadDTO;
import com.example.prepractica.domain.dtos.GeneralResponse;
import com.example.prepractica.services.EspecialidadService;
import com.example.prepractica.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createCitaMedica (@RequestBody @Valid CreateEspecialidadDTO info){

        especialidadService.CreateEspecialidad(info);

        return GeneralResponse.getResponse(HttpStatus.OK, "Create Especialidad successful");
    }
}
