package ro.musiclover.manicureappointments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;
import ro.musiclover.manicureappointments.service.implementation.AppointmentService;

import javax.validation.Valid;

@RestController
@RequestMapping("appointment")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Validated
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("create")
    public AppointmentResponse createAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest) {
        return appointmentService.createAppointment(appointmentRequest);
    }
}