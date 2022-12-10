package ro.musiclover.manicureappointments.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.mapper.CustomerMapper;
import ro.musiclover.manicureappointments.mapper.ManicuristMapper;
import ro.musiclover.manicureappointments.model.UpdateRequest;
import ro.musiclover.manicureappointments.model.appointment.*;
import ro.musiclover.manicureappointments.service.implementation.AppointmentService;
import ro.musiclover.manicureappointments.service.implementation.CustomerService;
import ro.musiclover.manicureappointments.service.implementation.ManicuristService;
import ro.musiclover.manicureappointments.service.implementation.NailsCareService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AppointmentWebController {

    private final AppointmentService appointmentService;
    private final NailsCareService nailsService;

    private final ManicuristService manicuristService;
    private final CustomerService customerService;

    private final ManicuristMapper manicuristMapper;
    private final CustomerMapper customerMapper;


    @GetMapping("/appointment")
    public String goToAppointmentsPage() {
        return "appointmentPage";
    }

    @GetMapping("/appointment/allAppointments")
    public String allAppointments(Model model) {
        List<AppointmentResponse> allAppointments = appointmentService.findAll();
        model.addAttribute("appointments", allAppointments);
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allServices", nailsService.allServices());
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

    @PostMapping("/appointment/update-date")
    public String updateDate(@ModelAttribute(value = "updateDateRequest") RequestUpdateDate request,
                             Model model) {
        appointmentService.updateAppointmentDate(request.getId(), request);
        model.addAttribute("appointments", appointmentService.findAll());
        return "allAppointmentsPage";
    }

    @PostMapping("/appointment/update-time")
    public String updateTime(@ModelAttribute(value = "updateTimeRequest") RequestUpdateTime request,
                             Model model) {
        appointmentService.updateAppointmentTime(request.getId(), request);
        model.addAttribute("appointments", appointmentService.findAll());
        return "allAppointmentsPage";
    }

    @PostMapping("/appointment/update-customer")
    public String updateCustomer(@ModelAttribute(value = "updateCustomerRequest") RequestUpdateCustomer request,
                                 Model model) {
        appointmentService.updateCustomer(request.getId(), request);
        model.addAttribute("appointments", appointmentService.findAll());
        return "allAppointmentsPage";
    }

    @PostMapping("/appointment/update-services")
    public String updateServices(@ModelAttribute(value = "updateServicesRequest") RequestUpdateServicesForWeb request,
                                 Model model) {
        RequestUpdateServices requestUpdateServices = RequestUpdateServices.builder()
                .nailsServicesIds(request.getNailsServicesIds())
                .build();
        appointmentService.updateNailsServices(request.getId(), requestUpdateServices);
        model.addAttribute("appointments", appointmentService.findAll());
        return "allAppointmentsPage";
    }

    @GetMapping("/appointment/goToUpdateServices")
    public String goToUpdateServices(@ModelAttribute(value = "updateRequest") UpdateRequest request, Model model) {
        model.addAttribute("appointmentId", request.getId());
        model.addAttribute("servicesFromDB", nailsService.allServices());
        return "updateServicesPage";
    }
}
