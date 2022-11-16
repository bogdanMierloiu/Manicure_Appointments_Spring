package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.mapper.CustomerMapper;
import ro.musiclover.manicureappointments.model.customer.CustomerRequest;
import ro.musiclover.manicureappointments.model.customer.CustomerResponse;
import ro.musiclover.manicureappointments.repository.CustomerRepository;
import ro.musiclover.manicureappointments.service.interfaces.ICustomer;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService extends Base<Customer> implements ICustomer {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        validatePhoneNumber(customerRequest.getPhoneNumber());
        Customer customer = customerMapper.map(customerRequest);
        return customerMapper.map(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return null;
    }

    @Override
    public CustomerResponse findCustomerById(Integer id) {
        return null;
    }

    @Override
    public void updateCustomer(Integer id, CustomerRequest customerRequest) {

    }

    @Override
    public void deleteCustomer(Integer id) {

    }
}
