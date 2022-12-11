package ro.musiclover.manicureappointments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.musiclover.manicureappointments.model.appointment.*;
import ro.musiclover.manicureappointments.service.AppointmentService;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

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

    @GetMapping("find/{id}")
    public AppointmentResponse findById(@PathVariable Integer id) {
        return appointmentService.findById(id);
    }


    @GetMapping("find/date/{date}")
    public List<AppointmentResponse> findByDate(@PathVariable Date date){
        return appointmentService.findByAppointmentDate(date);
    }
    @GetMapping("list")
    public List<AppointmentResponse> findAll() {
        return appointmentService.findAll();
    }

    @PatchMapping("update/date/{id}")
    public void updateDate(@PathVariable Integer id, @RequestBody @Valid RequestUpdateDate requestUpdateDate) {
        appointmentService.updateAppointmentDate(id, requestUpdateDate);
    }

    @PatchMapping("update/time/{id}")
    public void updateTime(@PathVariable Integer id, @RequestBody @Valid RequestUpdateTime requestUpdateTime) {
        appointmentService.updateAppointmentTime(id, requestUpdateTime);
    }

    @PatchMapping("update/services/{id}")
    public void updateServices(@PathVariable Integer id, @RequestBody RequestUpdateServices requestUpdateServices) {
        appointmentService.updateNailsServices(id, requestUpdateServices);
    }

    @PatchMapping("update/customer/{id}")
    public void updateCustomer(@PathVariable Integer id, @RequestBody RequestUpdateCustomer requestUpdateCustomer) {
        appointmentService.updateCustomer(id, requestUpdateCustomer);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        appointmentService.delete(id);
    }

}
