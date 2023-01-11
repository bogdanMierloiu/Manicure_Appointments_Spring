package ro.musiclover.manicureappointments.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.model.customer.CustomerDetailResponse;
import ro.musiclover.manicureappointments.model.customer.CustomerRequest;
import ro.musiclover.manicureappointments.model.customer.CustomerResponse;

import java.util.List;

@ComponentScan
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer map(CustomerRequest customerRequest);

    CustomerResponse map(Customer customer);

    List<CustomerResponse> map(List<Customer> allCustomers);





}
