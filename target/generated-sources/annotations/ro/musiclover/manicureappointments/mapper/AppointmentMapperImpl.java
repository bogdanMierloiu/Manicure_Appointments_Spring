package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;
import ro.musiclover.manicureappointments.model.customer.CustomerResponseForAppointment;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponseForAppointment;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-25T13:32:41+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public Appointment map(AppointmentRequest appointmentRequest) {
        if ( appointmentRequest == null ) {
            return null;
        }

        Appointment.AppointmentBuilder appointment = Appointment.builder();

        appointment.id( appointmentRequest.getId() );
        appointment.appointmentDate( appointmentRequest.getAppointmentDate() );
        appointment.appointmentTime( appointmentRequest.getAppointmentTime() );

        return appointment.build();
    }

    @Override
    public AppointmentResponse map(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentResponse appointmentResponse = new AppointmentResponse();

        appointmentResponse.setId( appointment.getId() );
        appointmentResponse.setAppointmentDate( appointment.getAppointmentDate() );
        appointmentResponse.setAppointmentTime( appointment.getAppointmentTime() );
        appointmentResponse.setManicurist( manicuristToManicuristResponseForAppointment( appointment.getManicurist() ) );
        appointmentResponse.setCustomer( customerToCustomerResponseForAppointment( appointment.getCustomer() ) );
        if ( appointmentResponse.getNailsServices() != null ) {
            List<NailsServiceResponse> list = nailsServiceSetToNailsServiceResponseList( appointment.getNailsServices() );
            if ( list != null ) {
                appointmentResponse.getNailsServices().addAll( list );
            }
        }

        return appointmentResponse;
    }

    @Override
    public List<AppointmentResponse> map(List<Appointment> all) {
        if ( all == null ) {
            return null;
        }

        List<AppointmentResponse> list = new ArrayList<AppointmentResponse>( all.size() );
        for ( Appointment appointment : all ) {
            list.add( map( appointment ) );
        }

        return list;
    }

    protected ManicuristResponseForAppointment manicuristToManicuristResponseForAppointment(Manicurist manicurist) {
        if ( manicurist == null ) {
            return null;
        }

        ManicuristResponseForAppointment manicuristResponseForAppointment = new ManicuristResponseForAppointment();

        manicuristResponseForAppointment.setFirstName( manicurist.getFirstName() );
        manicuristResponseForAppointment.setLastName( manicurist.getLastName() );

        return manicuristResponseForAppointment;
    }

    protected CustomerResponseForAppointment customerToCustomerResponseForAppointment(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponseForAppointment customerResponseForAppointment = new CustomerResponseForAppointment();

        customerResponseForAppointment.setId( customer.getId() );
        customerResponseForAppointment.setFirstName( customer.getFirstName() );
        customerResponseForAppointment.setLastName( customer.getLastName() );

        return customerResponseForAppointment;
    }

    protected NailsServiceResponse nailsServiceToNailsServiceResponse(NailsService nailsService) {
        if ( nailsService == null ) {
            return null;
        }

        NailsServiceResponse nailsServiceResponse = new NailsServiceResponse();

        nailsServiceResponse.setId( nailsService.getId() );
        nailsServiceResponse.setServiceName( nailsService.getServiceName() );
        nailsServiceResponse.setPrice( nailsService.getPrice() );

        return nailsServiceResponse;
    }

    protected List<NailsServiceResponse> nailsServiceSetToNailsServiceResponseList(Set<NailsService> set) {
        if ( set == null ) {
            return null;
        }

        List<NailsServiceResponse> list = new ArrayList<NailsServiceResponse>( set.size() );
        for ( NailsService nailsService : set ) {
            list.add( nailsServiceToNailsServiceResponse( nailsService ) );
        }

        return list;
    }
}
