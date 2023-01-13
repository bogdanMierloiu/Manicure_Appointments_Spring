package ro.musiclover.manicureappointments.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.musiclover.manicureappointments.model.appointment.*;
import ro.musiclover.manicureappointments.service.AppointmentService;

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
    public AppointmentResponse createAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest) {
        return appointmentService.createAppointment(appointmentRequest);
    }
    @GetMapping("find/{id}")
    public AppointmentResponse findById(@PathVariable Integer id) {
        return appointmentService.findById(id);
    }

    @GetMapping("find/date")
    public List<AppointmentResponse> findByDate(@RequestBody DateRequest date) {
        return appointmentService.findByAppointmentByDate(date);
    }
    @GetMapping("/find/between")
    public List<AppointmentResponse> findBetween(@RequestBody DateBetweenRequest dateBetweenRequest) {
        return appointmentService.listBetween(dateBetweenRequest.getDateFrom(), dateBetweenRequest.getDateTo());
    }
    @GetMapping("/list")
    public List<AppointmentResponse> findAll() {
        return appointmentService.findAll();
    }

    @PatchMapping("update/date")
    public void updateDateTime(@RequestBody @Valid RequestUpdateDate requestUpdateDate) {
        appointmentService.updateAppointmentDateTime(requestUpdateDate);
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

    @GetMapping("revenue/period")
    public Integer revenueForPeriod(@RequestBody DateBetweenRequest dateBetweenRequest) {
        return appointmentService.revenueForPeriod(dateBetweenRequest);
    }

    @GetMapping("revenue/day")
    public Integer revenueForDay(@RequestBody DateRequest dateRequest) {
        return appointmentService.revenueForDay(dateRequest);
    }

}
