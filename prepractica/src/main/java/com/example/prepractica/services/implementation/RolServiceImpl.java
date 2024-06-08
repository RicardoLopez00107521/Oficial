package com.example.prepractica.services.implementation;

import com.example.prepractica.domain.entities.Rol;
import com.example.prepractica.domain.dtos.Rol.CreateRolDTO;
import com.example.prepractica.repositories.RolRepository;
import com.example.prepractica.services.RolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public void createRol(CreateRolDTO info) {
        Rol rol = new Rol();
        rol.setRol(info.getRol());
        rolRepository.save(rol);
    }
}
