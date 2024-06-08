package com.example.prepractica.services;

import com.example.prepractica.domain.dtos.Prescripcion.CreatePrescripcionDTO;
import com.example.prepractica.domain.entities.CitaMedica;

public interface PrescripcionService {

    void createPrescripcion(CitaMedica citaMedica, CreatePrescripcionDTO info);
}
