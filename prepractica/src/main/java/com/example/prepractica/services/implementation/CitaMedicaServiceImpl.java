package com.example.prepractica.services.implementation;

import com.example.prepractica.domain.dtos.CitaMedica.CreateCitaMedicaDTO;
import com.example.prepractica.domain.dtos.CitaMedica.ResponseAppointmentDTO;
import com.example.prepractica.domain.entities.CitaMedica;
import com.example.prepractica.domain.entities.Rol;
import com.example.prepractica.domain.entities.User;
import com.example.prepractica.repositories.CitaMedicaRepository;
import com.example.prepractica.repositories.RolRepository;
import com.example.prepractica.repositories.UserRepository;
import com.example.prepractica.services.CitaMedicaService;
import com.example.prepractica.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CitaMedicaServiceImpl implements CitaMedicaService {

    private final CitaMedicaRepository citaMedicaRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
    private final RolRepository rolRepository;

    public CitaMedicaServiceImpl(CitaMedicaRepository citaMedicaRepository, UserRepository userRepository, UserService userService, UserServiceImpl userServiceImpl, RolRepository rolRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
        this.userRepository = userRepository;
        this.userServiceImpl = userServiceImpl;
        this.rolRepository = rolRepository;
    }

    @Override
    public void CreateCitaMedica(CreateCitaMedicaDTO info, Date fechaHoraInicioMapped, User user) {

        CitaMedica citaMedica = new CitaMedica();
        citaMedica.setUser(user);
        citaMedica.setFechaHoraInicio(fechaHoraInicioMapped);
        citaMedica.setDescripcion(info.getDescripcion());
        citaMedica.setState("Pendent");

        citaMedicaRepository.save(citaMedica);
    }

    @Override
    public List<CitaMedica> findByUserAndDate(String user, Date date) {
        return citaMedicaRepository
                .findAll()
                .stream()
                .filter(citaMedica -> citaMedica.getUser().getEmail().equals(user))
                .filter(citaMedica -> citaMedica.getFechaHoraInicio().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public CitaMedica GetCitaMedicaByUUID(UUID id) {
        return citaMedicaRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Date validFechaHoraInicio(String fechaHoraInicio) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fechaHoraInicio);
        } catch (ParseException ex) {
            return null;
        }
    }

    @Override
    public List<CitaMedica> getAllCitasMedicas() {
        return citaMedicaRepository.findAll();
    }

    @Override
    public void ResponseAppointment(List<CitaMedica> appointments, ResponseAppointmentDTO info, User user) {

        @Override
        public void agregarRol(User user, Rol rol) {
            // Agregar el rol al usuario si aún no lo tiene
            if (!user.getRoles().contains(rol)) {
                user.getRoles().add(rol);

                // Guardar el usuario actualizado en la base de datos
                userRepository.save(user);
            }
        }
    }
}
