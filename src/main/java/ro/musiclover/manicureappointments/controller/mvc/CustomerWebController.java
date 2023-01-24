package ro.musiclover.manicureappointments.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponseForCustomerDetail;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceForCustomerDetail;
import ro.musiclover.manicureappointments.model.utils.IdRequest;
import ro.musiclover.manicureappointments.model.customer.*;
import ro.musiclover.manicureappointments.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    @GetMapping("/google-charts")
    public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Under 22 years", customerService.under22());
        graphData.put("Between 23 and 30 years", customerService.between23and30());
        graphData.put("Between 31 and 40 years ", customerService.between31and40());
        graphData.put("Older than 41 years", customerService.olderThan41());
        model.addAttribute("chartData", graphData);
        return "google-charts";
    }
    @GetMapping("/allActiveCustomers")
    public String goToAllActiveCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllActiveCustomers());
        return "allCustomersPage";
    }
    @GetMapping("/goToCreateCustomerPage")
    public String goToCreateCustomerPage() {
        return "customerCreatePage";
    }

    @PostMapping("/create-new-customer")
    public String createCustomer(@ModelAttribute CustomerRequest customerRequest,
                                 Model model) {
        customerService.createCustomer(customerRequest);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }
    @GetMapping("/details")
    public String viewDetails(@ModelAttribute IdRequest request, Model model) {
        CustomerResponse customerResponse = customerService.findCustomerById(request.getId());
        model.addAttribute("customer", customerResponse);
        return "customerDetailsPage";
    }
    @GetMapping("/customerWithDetails")
    public String customerWithDetails(@ModelAttribute IdRequest request, Model model) {
        CustomerDetailResponse customer = customerService.customerWithAppointments(request.getId());
        model.addAttribute("customer", customer);
        return "customerWithAppointmentsPage";
    }
    @GetMapping("/findByFirstName")
    public String findByFirstName(@ModelAttribute FindByNameRequest request, Model model) {
        CustomerResponse customer = customerService.findByFirstName(request.getFirstName());
        model.addAttribute("customer", customer);
        return "customerDetailsPage";
    }
    @GetMapping("/goToUpdateName")
    public String goToUpdateName(@ModelAttribute IdRequest request, Model model) {
        CustomerResponse customer = customerService.findCustomerById(request.getId());
        model.addAttribute("customer", customer);
        return "customerUpdateNamePage";
    }
    @PostMapping("/update-name")
    public String updateFirstName(@ModelAttribute RequestUpdateNameCustomer request,
                                  Model model) {
        customerService.updateFirstLastName(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }
    @PostMapping("/update-phoneNumber")
    public String updateFirstName(@ModelAttribute RequestUpdatePhoneNumberCustomer request,
                                  Model model) {
        customerService.updatePhoneNumber(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }
    @PostMapping("/update-birthdate")
    public String updateFirstName(@ModelAttribute RequestUpdateBirthDateCustomer request,
                                  Model model) {
        customerService.updateBirthDate(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }
    @PostMapping("/update-email")
    public String updateFirstName(@ModelAttribute RequestUpdateEmailCustomer request,
                                  Model model) {
        customerService.updateEmail(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }
    @PostMapping("/update-status")
    public String updateCustomerStatus(@ModelAttribute CustomerUpdateStatus request,
                                       Model model) {
        customerService.updateStatus(request.getId(), request);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }
    @PostMapping("/deleteById")
    public String deleteById(@ModelAttribute(value = "deleteRequest") IdRequest request, Model model) {
        customerService.deleteById(request.getId());
        model.addAttribute("customers", customerService.getAllCustomers());
        return "allCustomersPage";
    }

}
