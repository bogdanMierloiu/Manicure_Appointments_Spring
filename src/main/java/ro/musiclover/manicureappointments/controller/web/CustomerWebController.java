package ro.musiclover.manicureappointments.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.musiclover.manicureappointments.model.IdRequest;
import ro.musiclover.manicureappointments.model.customer.*;
import ro.musiclover.manicureappointments.service.CustomerService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/customer")
public class CustomerWebController {

    private final CustomerService customerService;

    @GetMapping
    public String goToCustomerPage() {
        return "customerPage";
    }

    @GetMapping("/allCustomers")
    public String goToAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @GetMapping("/allActiveCustomers")
    public String goToAllActiveCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllActiveCustomers());
        return "allCustomersPage";
    }

    @GetMapping("/details")
    public String viewDetails(@ModelAttribute(value = "id") IdRequest request, Model model) {
        CustomerResponse customerResponse = customerService.findCustomerById(request.getId());
        model.addAttribute("customer", customerResponse);
        return "customerDetailsPage";
    }


    @GetMapping("/goToCreateCustomerPage")
    public String goToCreateCustomerPage() {
        return "customerCreatePage";
    }

    @PostMapping("/create-new-customer")
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

    @GetMapping("/findByFirstName")
    public String findByFirstName(@ModelAttribute(value = "name") FindByNameRequest request, Model model) {
        List<CustomerDetailResponse> customersResult = customerService.findByFirstName(request.getFirstName());
        model.addAttribute("customers", customersResult);
        return "resultCustomerPage";
    }

    @PostMapping("/deleteById")
    public String deleteById(@ModelAttribute(value = "deleteRequest") IdRequest request, Model model) {
        customerService.deleteById(request.getId());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/update-firstname")
    public String updateFirstName(@ModelAttribute(value = "updateFirstName") RequestUpdateFirstNameCustomer request,
                                  Model model) {
        customerService.updateFirstName(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/update-lastname")
    public String updateFirstName(@ModelAttribute(value = "updateLastName") RequestUpdateLastNameCustomer request,
                                  Model model) {
        customerService.updateLastName(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/update-phoneNumber")
    public String updateFirstName(@ModelAttribute(value = "updatePhoneNumber") RequestUpdatePhoneNumberCustomer request,
                                  Model model) {
        customerService.updatePhoneNumber(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/update-birthdate")
    public String updateFirstName(@ModelAttribute(value = "updateBirthDate") RequestUpdateBirthDateCustomer request,
                                  Model model) {
        customerService.updateBirthDate(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/update-email")
    public String updateFirstName(@ModelAttribute(value = "updateEmail") RequestUpdateEmailCustomer request,
                                  Model model) {
        customerService.updateEmail(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

    @PostMapping("/update-status")
    public String updateCustomerStatus(@ModelAttribute(value = "updateRequest") CustomerUpdateStatus request,
                                       Model model) {
        customerService.updateStatus(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }


}
