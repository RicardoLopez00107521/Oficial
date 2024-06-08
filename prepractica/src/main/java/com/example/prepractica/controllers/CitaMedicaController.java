package com.example.prepractica.controllers;

import com.example.prepractica.domain.dtos.CitaMedica.CreateCitaMedicaDTO;
import com.example.prepractica.domain.dtos.CitaMedica.ResponseAppointmentDTO;
import com.example.prepractica.domain.dtos.GeneralResponse;
import com.example.prepractica.domain.dtos.User.RegisterDTO;
import com.example.prepractica.domain.entities.CitaMedica;
import com.example.prepractica.domain.entities.Rol;
import com.example.prepractica.domain.entities.User;
import com.example.prepractica.services.CitaMedicaService;
import com.example.prepractica.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/citaMedica")
public class CitaMedicaController {

    private final CitaMedicaService citaMedicaService;

    private final UserService userService;

    public CitaMedicaController(CitaMedicaService citaMedicaService, UserService userService) {
        this.citaMedicaService = citaMedicaService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralResponse> createCitaMedica (@RequestBody @Valid CreateCitaMedicaDTO info){

        User user = userService.findUserAuthenticated();

        Date fechaHoraInicioMapped = citaMedicaService.validFechaHoraInicio(info.getFechaHoraInicio());

        if (fechaHoraInicioMapped == null) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Invalid date format that must be yyyy-MM-dd");
        }

        citaMedicaService.CreateCitaMedica(info, fechaHoraInicioMapped, user);

        return GeneralResponse.getResponse(HttpStatus.OK, "Create Cita Medica successful");
    }

    @GetMapping("/getAll")
    public ResponseEntity<GeneralResponse> getAllCitasMedicas (){

        List<CitaMedica> citaMedica = citaMedicaService.getAllCitasMedicas();

        return GeneralResponse.getResponse(HttpStatus.OK, citaMedica);
    }

    @PatchMapping("/confirmAppointment/{userEmail}")
    public ResponseEntity<GeneralResponse> confirmAppointment (@PathVariable String userEmail, @RequestBody ResponseAppointmentDTO info) {

        User isUser = userService.findUserByIdentifier(userEmail);

        if (isUser == null) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "User not found");
        }

        Date thisDate = citaMedicaService.validFechaHoraInicio(info.getAppointmentDate());

        if (thisDate == null) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Invalid date format that must be yyyy-MM-dd HH:mm:ss");
        }

        List<CitaMedica> haveAppointment = citaMedicaService.findByUserAndDate(isUser.getEmail(), thisDate);

        if (haveAppointment == null) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Appointment not found");
        }

        citaMedicaService.ResponseAppointment(haveAppointment, info, isUser);

        return GeneralResponse.getResponse(HttpStatus.OK, "Appointment confirmation successful");
    }

    @GetMapping("/findRolesByUser")
    public ResponseEntity<GeneralResponse> findRolesByUser () {
        User user = userService.findUserAuthenticated();
        List<Rol> roles = userService.findRolesByUser(user);

        if (roles == null) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "No roles found");
        }

        return GeneralResponse.getResponse(HttpStatus.OK, roles);
    }

}
