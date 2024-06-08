package com.example.prepractica.controllers;

import com.example.prepractica.domain.dtos.GeneralResponse;
import com.example.prepractica.domain.dtos.Prescripcion.CreatePrescripcionDTO;
import com.example.prepractica.domain.dtos.Rol.CreateRolDTO;
import com.example.prepractica.domain.entities.CitaMedica;
import com.example.prepractica.domain.entities.User;
import com.example.prepractica.repositories.CitaMedicaRepository;
import com.example.prepractica.services.CitaMedicaService;
import com.example.prepractica.services.HistorialService;
import com.example.prepractica.services.PrescripcionService;
import com.example.prepractica.services.RolService;
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
@RequestMapping("/api/prescripcion")
public class PrescripcionController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    @Autowired
    private PrescripcionService prescripcionService;

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createPrescripcion (@RequestBody @Valid CreatePrescripcionDTO info){

        CitaMedica citaMedica = citaMedicaService.GetCitaMedicaByUUID(info.getFk_citaMedica());

        if (citaMedica != null) {
            prescripcionService.createPrescripcion(citaMedica, info);
        }
        else {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Invalid FK citaMedica");
        }

        return GeneralResponse.getResponse(HttpStatus.OK, "Create prescripcion successful");
    }
}
