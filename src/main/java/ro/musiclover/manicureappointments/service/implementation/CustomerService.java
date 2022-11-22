package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.exception.BusinessException;
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
        checkDuplicate(customerRequest);
        validatePhoneNumber(customerRequest.getPhoneNumber());
        Customer customer = customerMapper.map(customerRequest);
        return customerMapper.map(customerRepository.save(customer));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerMapper.map(customerRepository.findAll());
    }

    @Override
    public CustomerResponse findCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("Customer with id: %s not found", id))
        );
        return customerMapper.map(customer);
    }

    @Override
    public void updateCustomer(Integer id, CustomerRequest customerRequest) {
        validatePhoneNumber(customerRequest.getPhoneNumber());
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("Customer with id: %s not found", id)
                )
        );
        customerToUpdate.setFirstName(customerRequest.getFirstName());
        customerToUpdate.setLastName(customerRequest.getLastName());
        customerToUpdate.setPhoneNumber(customerRequest.getPhoneNumber());
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customerToDelete = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("Customer with id: %s not found", id)
                )
        );
        customerRepository.delete(customerToDelete);
    }

    public void checkDuplicate(CustomerRequest customerRequest) {
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getFirstName().equals(customerRequest.getFirstName()) &&
                    customer.getLastName().equals(customerRequest.getLastName())) {
                throw new BusinessException("This Customer already exist");
            }
        }
    }


}
