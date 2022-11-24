package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.model.customer.CustomerDetailResponse;
import ro.musiclover.manicureappointments.model.customer.CustomerRequest;
import ro.musiclover.manicureappointments.model.customer.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface ICustomer {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomers();
    CustomerDetailResponse findByIdWithDetails(Integer id);

    CustomerResponse findCustomerById(Integer id);

    void updateCustomer(Integer id, CustomerRequest customerRequest);

    void deleteCustomer(Integer id);
}
