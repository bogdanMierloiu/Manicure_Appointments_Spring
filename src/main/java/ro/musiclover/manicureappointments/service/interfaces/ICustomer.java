package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomer {

    void createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Optional<Customer> findCustomerById(Integer id);

    void updateCustomer(Integer id, Customer customer);

    void deleteCustomer(Integer id);
}
