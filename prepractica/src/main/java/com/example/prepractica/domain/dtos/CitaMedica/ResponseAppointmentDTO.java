package com.example.prepractica.domain.dtos.CitaMedica;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ResponseAppointmentDTO {

    @NotBlank
    private String appointmentDate;

    @NotBlank
    @Pattern(regexp = "Accepted|Rejected", message = "State must be either 'Accepted' or 'Rejected'")
    private String state;
}
