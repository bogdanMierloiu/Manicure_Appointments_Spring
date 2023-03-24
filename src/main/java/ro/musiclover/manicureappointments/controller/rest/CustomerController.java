package ro.musiclover.manicureappointments.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.musiclover.manicureappointments.model.customer.CustomerDetailResponse;
import ro.musiclover.manicureappointments.model.customer.CustomerRequest;
import ro.musiclover.manicureappointments.model.customer.CustomerResponse;
import ro.musiclover.manicureappointments.model.customer.CustomerUpdateStatus;
import ro.musiclover.manicureappointments.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Validated
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("create")
    public CustomerResponse createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping("list")
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("list/active")
    public List<CustomerResponse> getAllActiveCustomers() {
        return customerService.getAllActiveCustomers();
    }

    @GetMapping("find/{id}")
    public CustomerResponse findCustomerById(@PathVariable Integer id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("find/details/{id}")
    public CustomerDetailResponse findByIdWithDetails(@PathVariable Integer id) {
        return customerService.findByIdWithDetails(id);
    }

    @GetMapping("find/name/{firstName}")
    public CustomerResponse findByName(@PathVariable String firstName) {
        return customerService.findByFirstName(firstName);
    }


    @PatchMapping("/update")
    public void updateCustomerById(@RequestBody CustomerRequest customerRequest) {
        customerService.updateCustomer(customerRequest);
    }

    @PatchMapping("update/status/{id}")
    public void updateStatus(@PathVariable Integer id, @RequestBody CustomerUpdateStatus customerUpdateStatus) {
        customerService.updateStatus(id, customerUpdateStatus);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        customerService.deleteById(id);
    }

    @GetMapping("/customer-with-appointments/{id}")
    public CustomerDetailResponse customerWithAppointments(@PathVariable Integer id) {
        return customerService.customerWithAppointments(id);
    }

    @GetMapping("age/{id}")
    public int getAge(@PathVariable int id) {
        return customerService.getAge(id);
    }

    @GetMapping("under_22")
    public int under22() {
        return customerService.under22();
    }

    @GetMapping("between23_30")
    public int between22and30() {
        return customerService.between23and30();
    }


}
