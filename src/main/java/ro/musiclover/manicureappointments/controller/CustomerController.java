package ro.musiclover.manicureappointments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.service.implementation.CustomerService;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

}
