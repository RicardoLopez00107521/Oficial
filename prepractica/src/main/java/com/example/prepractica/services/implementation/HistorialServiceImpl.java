package com.example.prepractica.services.implementation;

import com.example.prepractica.domain.dtos.Historial.CreateHistorialDTO;
import com.example.prepractica.domain.entities.Historial;
import com.example.prepractica.domain.entities.User;
import com.example.prepractica.repositories.HistorialRepository;
import com.example.prepractica.services.HistorialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class HistorialServiceImpl implements HistorialService {

    private final HistorialRepository historialRepository;

    public HistorialServiceImpl(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    @Override
    public void CreateHistorial(User user, CreateHistorialDTO info) {

            Historial historial = new Historial();
            historial.setDescripcion(info.getDescripcion());
            historial.setUser(user);
            historialRepository.save(historial);
    }
}
