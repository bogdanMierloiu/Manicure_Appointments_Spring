package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.CustomerMapper;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponseForCustomerDetail;
import ro.musiclover.manicureappointments.model.customer.CustomerDetailResponse;
import ro.musiclover.manicureappointments.model.customer.CustomerRequest;
import ro.musiclover.manicureappointments.model.customer.CustomerResponse;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceForCustomerDetail;
import ro.musiclover.manicureappointments.repository.CustomerRepository;
import ro.musiclover.manicureappointments.service.interfaces.ICustomer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
    public CustomerDetailResponse findByIdWithDetails(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new BusinessException(
                "Customer not found"));

        CustomerDetailResponse customerDetailResponse = new CustomerDetailResponse();

        customerDetailResponse.setFirstName(customer.getFirstName());
        customerDetailResponse.setLastName(customer.getLastName());
        customerDetailResponse.setAppointments(new ArrayList<>());

        for (Appointment appointment : customer.getAppointments()) {
            AppointmentResponseForCustomerDetail appointmentResponse = new AppointmentResponseForCustomerDetail();
            appointmentResponse.setAppointmentDate(appointment.getAppointmentDate());
            appointmentResponse.setAppointmentTime(appointment.getAppointmentTime());
            appointmentResponse.setNailsServices(new ArrayList<>());
            for (NailsService nailsService : appointment.getNailsServices()) {
                NailsServiceForCustomerDetail nailsServiceForCustomerDetail = new NailsServiceForCustomerDetail();
                nailsServiceForCustomerDetail.setServiceName(nailsService.getServiceName());
                appointmentResponse.getNailsServices().add(nailsServiceForCustomerDetail);
            }
            customerDetailResponse.getAppointments().add(appointmentResponse);
        }
        return customerDetailResponse;
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
