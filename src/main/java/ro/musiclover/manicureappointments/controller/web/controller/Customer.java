package ro.musiclover.manicureappointments.controller.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.model.UpdateRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponseForCustomerDetail;
import ro.musiclover.manicureappointments.model.customer.*;
import ro.musiclover.manicureappointments.service.implementation.CustomerService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class Customer {

    private final CustomerService customerService;

    @GetMapping("customer")
    public String goToCustomerPage() {
        return "customerPage";
    }

    @GetMapping("/customer/allCustomers")
    public String goToAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("customer/update")
    public String updateCustomerStatus(@ModelAttribute(value = "deleteRequest") UpdateRequest request,
                                       @ModelAttribute(value = "status") CustomerUpdateStatus customerUpdateStatus,
                                       Model model) {
        customerService.updateStatus(request.getId(), customerUpdateStatus);

        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @GetMapping("customer/goToCreateCustomerPage")
    public String goToCreateCustomerPage() {
        return "customerCreatePage";
    }

    @PostMapping("customer/create-new-customer")
    public String createCustomer(@ModelAttribute(value = "createCustomerRequest") CreateCustomerRequest request,
                                 Model model) {
        CustomerRequest customerRequest = CustomerRequest.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .birthDate(request.getBirthDate())
                .email(request.getEmail())
                .active(true)
                .build();
        customerService.createCustomer(customerRequest);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @GetMapping("/customer/findByFirstName")
    public String findByFirstName(@ModelAttribute(value = "name") FindByNameRequest request, Model model) {
        model.addAttribute("customers", customerService.findByFirstName(request.getFirstName()));
        return "resultCustomerPage";
    }

}
