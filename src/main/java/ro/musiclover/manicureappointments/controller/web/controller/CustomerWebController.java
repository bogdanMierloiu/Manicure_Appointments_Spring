package ro.musiclover.manicureappointments.controller.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponseForCustomerDetail;
import ro.musiclover.manicureappointments.model.customer.*;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceForCustomerDetail;
import ro.musiclover.manicureappointments.service.implementation.CustomerService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CustomerWebController {

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

    @GetMapping("/customer/allActiveCustomers")
    public String goToAllActiveCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllActiveCustomers());
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
        List<CustomerDetailResponse> customersResult = customerService.findByFirstName(request.getFirstName());
        model.addAttribute("customers", customersResult);
        return "resultCustomerPage";
    }

    @PostMapping("/customer/update-firstname")
    public String updateFirstName(@ModelAttribute(value = "updateFirstName") RequestUpdateFirstNameCustomer request,
                                  Model model) {
        customerService.updateFirstName(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/customer/update-lastname")
    public String updateFirstName(@ModelAttribute(value = "updateLastName") RequestUpdateLastNameCustomer request,
                                  Model model) {
        customerService.updateLastName(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/customer/update-phoneNumber")
    public String updateFirstName(@ModelAttribute(value = "updatePhoneNumber") RequestUpdatePhoneNumberCustomer request,
                                  Model model) {
        customerService.updatePhoneNumber(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/customer/update-birthdate")
    public String updateFirstName(@ModelAttribute(value = "updateBirthDate") RequestUpdateBirthDateCustomer request,
                                  Model model) {
        customerService.updateBirthDate(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/customer/update-email")
    public String updateFirstName(@ModelAttribute(value = "updateEmail") RequestUpdateEmailCustomer request,
                                  Model model) {
        customerService.updateEmail(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("customer/update-status")
    public String updateCustomerStatus(@ModelAttribute(value = "updateRequest") CustomerUpdateStatus request,
                                       Model model) {
        customerService.updateStatus(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }


}
