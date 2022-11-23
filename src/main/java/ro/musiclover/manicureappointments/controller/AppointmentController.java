package ro.musiclover.manicureappointments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;
import ro.musiclover.manicureappointments.service.implementation.AppointmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("appointment")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Validated
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("create")
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.createAppointment(appointmentRequest);
    }

    @GetMapping("find/{id}")
    public AppointmentResponse findById(@PathVariable Integer id) {
        return appointmentService.findById(id);
    }

    @GetMapping("list")
    public List<AppointmentResponse> findAll() {
        return appointmentService.findAll();
    }
}
