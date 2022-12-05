package ro.musiclover.manicureappointments.controller.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.CreateAppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.FindByDateRequest;
import ro.musiclover.manicureappointments.service.implementation.AppointmentService;
import ro.musiclover.manicureappointments.service.implementation.CustomerService;
import ro.musiclover.manicureappointments.service.implementation.ManicuristService;
import ro.musiclover.manicureappointments.service.implementation.NailsServiceService;

@RequiredArgsConstructor
@Controller
public class Appointment {

    private final AppointmentService appointmentService;
    private final NailsServiceService nailsService;

    private final ManicuristService manicuristService;
    private final CustomerService customerService;


    @GetMapping("/appointment")
    public String goToAppointmentsPage() {
        return "appointmentPage";
    }

    @GetMapping("/appointment/allAppointments")
    public String allAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
        return "allAppointmentsPage";
    }

    @GetMapping("/appointment/goToCreateAppointmentPage")
    public String goToCreateAppointmentPage(Model model) {

        model.addAttribute("manicurists", manicuristService.allManicurists());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("services", nailsService.allServices());
        return "appointmentCreatePage";
    }

    @PostMapping("/appointment/create-new-appointment")
    public String createNewAppointment(@ModelAttribute(value = "createAppointmentRequest") CreateAppointmentRequest request,
                                       Model model) {
        AppointmentRequest appointmentRequest = AppointmentRequest.builder()
                .appointmentDate(request.getAppointmentDate())
                .appointmentTime(request.getAppointmentTime())
                .manicuristId(request.getManicuristId())
                .customerId(request.getCustomerId())
                .nailsServicesIds(request.getNailsServicesIds())
                .build();
        appointmentService.createAppointment(appointmentRequest);

        model.addAttribute("appointments", appointmentService.findAll());
        return "allAppointmentsPage";
    }

    @GetMapping("/appointment/findByDate")
    public String findByDate(@ModelAttribute(value = "dateRequest") FindByDateRequest request, Model model) {
        model.addAttribute("appointments", appointmentService.findByAppointmentDate(request.getAppointmentDate()));
        return "resultAppointmentPage";
    }

}
