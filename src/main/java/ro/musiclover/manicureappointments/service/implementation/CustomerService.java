package ro.musiclover.manicureappointments.service.implementation;

import org.springframework.stereotype.Service;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.repository.CustomerRepository;
import ro.musiclover.manicureappointments.service.interfaces.ICustomer;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService extends Base<Customer> implements ICustomer {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        super(Customer.class);
        this.customerRepository = customerRepository;
    }

    @Override
    public void createCustomer(Customer customer) {
        validateInputStrings(customer.getFirstName());
        validateInputStrings(customer.getLastName());
//        validatePhoneNumber(customer.getPhoneNumber());
        validateUnique(customer);
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Optional<Customer> findCustomerById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void updateCustomer(Integer id, Customer customer) {

    }

    @Override
    public void deleteCustomer(Integer id) {

    }

    public void validateUnique(Customer customer) {
        List<Customer> allCustomers = customerRepository.findAll();
        for (Customer customerFromList : allCustomers) {
            if (customer.equals(customerFromList)) {
                throw new IllegalArgumentException("This customer already exist");
            }
        }
    }

}
