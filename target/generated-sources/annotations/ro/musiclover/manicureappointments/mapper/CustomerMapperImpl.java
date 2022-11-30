package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.model.customer.CustomerRequest;
import ro.musiclover.manicureappointments.model.customer.CustomerResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-30T00:29:33+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer map(CustomerRequest customerRequest) {
        if ( customerRequest == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( customerRequest.getId() );
        customer.firstName( customerRequest.getFirstName() );
        customer.lastName( customerRequest.getLastName() );
        customer.phoneNumber( customerRequest.getPhoneNumber() );
        customer.birthDate( customerRequest.getBirthDate() );
        customer.email( customerRequest.getEmail() );
        customer.active( customerRequest.isActive() );

        return customer.build();
    }

    @Override
    public CustomerResponse map(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();

        customerResponse.setId( customer.getId() );
        customerResponse.setFirstName( customer.getFirstName() );
        customerResponse.setLastName( customer.getLastName() );
        customerResponse.setPhoneNumber( customer.getPhoneNumber() );
        customerResponse.setBirthDate( customer.getBirthDate() );
        customerResponse.setEmail( customer.getEmail() );
        customerResponse.setActive( customer.isActive() );

        return customerResponse;
    }

    @Override
    public List<CustomerResponse> map(List<Customer> allCustomers) {
        if ( allCustomers == null ) {
            return null;
        }

        List<CustomerResponse> list = new ArrayList<CustomerResponse>( allCustomers.size() );
        for ( Customer customer : allCustomers ) {
            list.add( map( customer ) );
        }

        return list;
    }
}
