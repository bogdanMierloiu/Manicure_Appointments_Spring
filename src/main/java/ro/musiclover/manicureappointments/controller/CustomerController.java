package ro.musiclover.manicureappointments.controller;

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
    public List<CustomerDetailResponse> findByName(@PathVariable String firstName) {
        return customerService.findByFirstName(firstName);
    }


    @PatchMapping("update/{id}")
    public void updateCustomerById(@PathVariable Integer id, @RequestBody @Valid CustomerRequest customerRequest) {
        customerService.updateCustomer(id, customerRequest);
    }


    @PatchMapping("update/status/{id}")
    public void updateStatus(@PathVariable Integer id, @RequestBody CustomerUpdateStatus customerUpdateStatus) {
        customerService.updateStatus(id, customerUpdateStatus);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {
        customerService.deleteById(id);
    }


}
