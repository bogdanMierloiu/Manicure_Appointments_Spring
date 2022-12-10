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
import ro.musiclover.manicureappointments.model.customer.*;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceForCustomerDetail;
import ro.musiclover.manicureappointments.repository.CustomerRepository;
import ro.musiclover.manicureappointments.repository.MyRepository;
import ro.musiclover.manicureappointments.service.interfaces.ICustomer;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService extends Base<Customer> implements ICustomer {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    private final MyRepository myRepository;


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
    public List<CustomerResponse> getAllActiveCustomers() {
        List<Customer> customersFromDb = customerRepository.findAll();
        List<CustomerResponse> customersForResponse = new ArrayList<>();
        for (Customer customer : customersFromDb) {
            if (customer.getActive()) {
                customersForResponse.add(customerMapper.map(customer));
            }
        }
        return customersForResponse;
    }

    @Override
    public CustomerResponse findCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("CustomerWebController with id: %s not found", id))
        );
        return customerMapper.map(customer);
    }

    @Override
    public CustomerDetailResponse findByIdWithDetails(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new BusinessException(
                "CustomerWebController not found"));

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
    public List<CustomerDetailResponse> findByFirstName(String firstName) {
        if (firstName.isBlank()) {
            throw new IllegalArgumentException("Invalid name");
        }
        List<Customer> customerListFromDB = customerRepository.findByFirstName(firstName);
        List<CustomerDetailResponse> customerDetailResponseList = new ArrayList<>();
        for (Customer customer : customerListFromDB) {
            CustomerDetailResponse customerDetailResponse = new CustomerDetailResponse();
            customerDetailResponse.setFirstName(customer.getFirstName());
            customerDetailResponse.setLastName(customer.getLastName());
            customerDetailResponse.setAppointments(new ArrayList<>());    // aici este gol
            for (Appointment appointment : customer.getAppointments()) {
                AppointmentResponseForCustomerDetail appointmentResponseForCustomerDetail = new AppointmentResponseForCustomerDetail();
                appointmentResponseForCustomerDetail.setAppointmentDate(appointment.getAppointmentDate());
                appointmentResponseForCustomerDetail.setAppointmentTime(appointment.getAppointmentTime());
                appointmentResponseForCustomerDetail.setNailsServices(new ArrayList<>());
                for (NailsService nailsService : appointment.getNailsServices()) {
                    NailsServiceForCustomerDetail nailsServiceForCustomerDetail = new NailsServiceForCustomerDetail();
                    nailsServiceForCustomerDetail.setServiceName(nailsService.getServiceName());
                    appointmentResponseForCustomerDetail.getNailsServices().add(nailsServiceForCustomerDetail);
                }
                customerDetailResponse.getAppointments().add(appointmentResponseForCustomerDetail);
            }
            customerDetailResponseList.add(customerDetailResponse);
        }
        if (customerDetailResponseList.isEmpty()) {
            throw new BusinessException("Not found");
        }
        return customerDetailResponseList;
    }

    @Override
    public void updateCustomer(Integer id, CustomerRequest customerRequest) {
        validatePhoneNumber(customerRequest.getPhoneNumber());
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("CustomerWebController with id: %s not found", id)
                )
        );
        customerToUpdate.setFirstName(customerRequest.getFirstName());
        customerToUpdate.setLastName(customerRequest.getLastName());
        customerToUpdate.setPhoneNumber(customerRequest.getPhoneNumber());
    }

    @Override
    public void updateStatus(Integer id, CustomerUpdateStatus customerUpdateStatus) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException("Not found")
        );
        customer.setActive(customerUpdateStatus.getActive());
    }

    @Override
    public void updateFirstName(Integer id, RequestUpdateFirstNameCustomer request) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("CustomerWebController with id: %s not found", id)
                )
        );
        customerToUpdate.setFirstName(request.getFirstName());
    }

    @Override
    public void updateLastName(Integer id, RequestUpdateLastNameCustomer request) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("CustomerWebController with id: %s not found", id)
                )
        );
        customerToUpdate.setLastName(request.getLastName());
    }

    @Override
    public void updatePhoneNumber(Integer id, RequestUpdatePhoneNumberCustomer request) {
        validatePhoneNumber(request.getPhoneNumber());
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("CustomerWebController with id: %s not found", id)
                )
        );
        customerToUpdate.setPhoneNumber(request.getPhoneNumber());
    }

    @Override
    public void updateBirthDate(Integer id, RequestUpdateBirthDateCustomer request) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("CustomerWebController with id: %s not found", id)
                )
        );
        customerToUpdate.setBirthDate(request.getBirthDate());
    }

    @Override
    public void updateEmail(Integer id, RequestUpdateEmailCustomer request) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("CustomerWebController with id: %s not found", id)
                )
        );
        customerToUpdate.setEmail(request.getEmail());
    }

    @Override
    public void deleteById(Integer id) {
        Customer customerToDelete = customerRepository.findById(id).orElseThrow(() ->
                new BusinessException("Not found"));
        customerRepository.deleteById(customerToDelete.getId());
    }

    public void checkDuplicate(CustomerRequest customerRequest) {
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getFirstName().equals(customerRequest.getFirstName()) &&
                    customer.getLastName().equals(customerRequest.getLastName())) {
                throw new BusinessException("This CustomerWebController already exist");
            }
        }
    }


}
