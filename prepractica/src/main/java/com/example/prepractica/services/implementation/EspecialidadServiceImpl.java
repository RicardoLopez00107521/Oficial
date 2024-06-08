package com.example.prepractica.services.implementation;

import com.example.prepractica.domain.dtos.Especialidad.CreateEspecialidadDTO;
import com.example.prepractica.domain.entities.Especialidad;
import com.example.prepractica.repositories.EspecialidadRepository;
import com.example.prepractica.services.EspecialidadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public void CreateEspecialidad(CreateEspecialidadDTO info) {
        Especialidad especialidad = new Especialidad();
        especialidad.setEspecialidad(info.getEspecialidad());

        especialidadRepository.save(especialidad);
    }
}
