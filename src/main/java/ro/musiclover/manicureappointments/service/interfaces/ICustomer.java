package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.model.customer.*;

import java.util.List;
import java.util.Optional;

public interface ICustomer {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomers();

    List<CustomerResponse> getAllActiveCustomers();
    CustomerDetailResponse findByIdWithDetails(Integer id);

    List<CustomerDetailResponse>  findByFirstName(String firstName);

    CustomerResponse findCustomerById(Integer id);

    void updateCustomer(Integer id, CustomerRequest customerRequest);

    void updateStatus(Integer id, CustomerUpdateStatus customerUpdateStatus);

    void updateFirstName(Integer id, RequestUpdateFirstNameCustomer request);
    void updateLastName(Integer id, RequestUpdateLastNameCustomer request);
    void updatePhoneNumber(Integer id, RequestUpdatePhoneNumberCustomer request);
    void updateBirthDate(Integer id, RequestUpdateBirthDateCustomer request);
    void updateEmail(Integer id, RequestUpdateEmailCustomer request);

}
