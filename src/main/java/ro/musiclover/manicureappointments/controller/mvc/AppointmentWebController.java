package ro.musiclover.manicureappointments.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.model.utils.IdRequest;
import ro.musiclover.manicureappointments.model.utils.UpdateRequest;
import ro.musiclover.manicureappointments.model.appointment.*;
import ro.musiclover.manicureappointments.service.AppointmentService;
import ro.musiclover.manicureappointments.service.CustomerService;
import ro.musiclover.manicureappointments.service.ManicuristService;
import ro.musiclover.manicureappointments.service.NailsCareService;
@RequiredArgsConstructor
@Controller
public class AppointmentWebController {
    private final AppointmentService appointmentService;
    private final NailsCareService nailsService;
    private final ManicuristService manicuristService;
    private final CustomerService customerService;

    @GetMapping("/appointment")
    public String goToAppointmentsPage() {
        return "appointmentPage";
    }

    @GetMapping("/appointment/allAppointments")
    public String allAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.findAll());
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
                .appointmentDateTime(request.getAppointmentDateTime())
                .manicuristId(request.getManicuristId())
                .customerId(request.getCustomerId())
                .nailsServicesIds(request.getNailsServicesIds())
                .build();
        appointmentService.createAppointment(appointmentRequest);
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allServices", nailsService.allServices());
        model.addAttribute("appointments", appointmentService.findAll());
        return "allAppointmentsPage";
    }

    @GetMapping("/appointment/findByDate")
    public String findByDate(@ModelAttribute(value = "dateRequest") FindByDateRequest request, Model model) {
        DateRequest dateRequest = DateRequest.builder()
                .date(request.getDate())
                .build();
        model.addAttribute("appointments", appointmentService.findByAppointmentByDate(dateRequest));
        return "resultAppointmentPage";
    }

    @GetMapping("/appointment/listBetween")
    public String listBetween(@ModelAttribute(value = "dateRequest") DateBetweenRequestForWeb request,
                              Model model) {
        model.addAttribute("appointmentsBetween", appointmentService.listBetween(
                request.getDateFrom().atStartOfDay(), request.getDateTo().plusDays(1).atStartOfDay()));
        return "appointmentBetweenPage";
    }

    @PostMapping("/appointment/update-date")
    public String updateDate(@ModelAttribute(value = "updateDateRequest") RequestUpdateDate request,
                             Model model) {
        appointmentService.updateAppointmentDateTime(request);
        model.addAttribute("appointments", appointmentService.findAll());
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allServices", nailsService.allServices());
        return "allAppointmentsPage";
    }

    @PostMapping("/appointment/update-customer")
    public String updateCustomer(@ModelAttribute(value = "updateCustomerRequest") RequestUpdateCustomer request,
                                 Model model) {
        appointmentService.updateCustomer(request.getId(), request);
        model.addAttribute("appointments", appointmentService.findAll());
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allServices", nailsService.allServices());
        return "allAppointmentsPage";
    }

    @PostMapping("/appointment/update-services")
    public String updateServices(@ModelAttribute(value = "updateServicesRequest") RequestUpdateServicesForWeb request,
                                 Model model) {
        RequestUpdateServices requestUpdateServices = RequestUpdateServices.builder()
                .nailsServicesIds(request.getNailsServicesIds())
                .build();
        appointmentService.updateNailsServices(request.getId(), requestUpdateServices);
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allServices", nailsService.allServices());
        model.addAttribute("appointments", appointmentService.findAll());
        return "allAppointmentsPage";
    }

    @GetMapping("/appointment/goToUpdateServices")
    public String goToUpdateServices(@ModelAttribute(value = "updateRequest") UpdateRequest request, Model model) {
        model.addAttribute("appointmentId", request.getId());
        model.addAttribute("servicesFromDB", nailsService.allServices());
        return "updateServicesPage";
    }

    @GetMapping("/appointment/delete")
    public String delete(@ModelAttribute(value = "deleteRequest") IdRequest request, Model model) {
        appointmentService.delete(request.getId());
        model.addAttribute("appointments", appointmentService.findAll());
        model.addAttribute("allCustomers", customerService.getAllCustomers());
        model.addAttribute("allServices", nailsService.allServices());
        return "allAppointmentsPage";
    }
}
