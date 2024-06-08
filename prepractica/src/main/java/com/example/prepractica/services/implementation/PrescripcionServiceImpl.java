package com.example.prepractica.services.implementation;

import com.example.prepractica.domain.dtos.Prescripcion.CreatePrescripcionDTO;
import com.example.prepractica.domain.entities.CitaMedica;
import com.example.prepractica.domain.entities.Prescripcion;
import com.example.prepractica.repositories.PrescripcionRepository;
import com.example.prepractica.services.PrescripcionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrescripcionServiceImpl implements PrescripcionService {

    private final PrescripcionRepository prescripcionRepository;

    public PrescripcionServiceImpl(PrescripcionRepository prescripcionRepository) {
        this.prescripcionRepository = prescripcionRepository;
    }

    @Override
    public void createPrescripcion(CitaMedica citaMedica, CreatePrescripcionDTO info) {
        Prescripcion prescripcion = new Prescripcion();
        prescripcion.setMedicamento(info.getMedicamento());
        prescripcion.setDosis(info.getDosis());
        prescripcion.setCitaMedica(citaMedica);
        prescripcionRepository.save(prescripcion);
    }
}
