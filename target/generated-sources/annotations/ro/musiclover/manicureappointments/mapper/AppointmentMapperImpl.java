package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;
import ro.musiclover.manicureappointments.model.customer.CustomerResponseForAppointment;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponseForAppointment;
import ro.musiclover.manicureappointments.model.nails_services.NailsCareResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-21T14:17:21+0200",
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
        appointment.appointmentDateTime( appointmentRequest.getAppointmentDateTime() );

        return appointment.build();
    }

    @Override
    public AppointmentResponse map(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentResponse appointmentResponse = new AppointmentResponse();

        appointmentResponse.setAppointmentDateTime( appointment.getAppointmentDateTime() );
        appointmentResponse.setId( appointment.getId() );
        appointmentResponse.setManicurist( manicuristToManicuristResponseForAppointment( appointment.getManicurist() ) );
        appointmentResponse.setCustomer( customerToCustomerResponseForAppointment( appointment.getCustomer() ) );
        if ( appointmentResponse.getNailsCares() != null ) {
            Set<NailsCareResponse> set = nailsCareSetToNailsCareResponseSet( appointment.getNailsCares() );
            if ( set != null ) {
                appointmentResponse.getNailsCares().addAll( set );
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

    protected NailsCareResponse nailsCareToNailsCareResponse(NailsCare nailsCare) {
        if ( nailsCare == null ) {
            return null;
        }

        NailsCareResponse nailsCareResponse = new NailsCareResponse();

        nailsCareResponse.setId( nailsCare.getId() );
        nailsCareResponse.setServiceName( nailsCare.getServiceName() );
        nailsCareResponse.setPrice( nailsCare.getPrice() );

        return nailsCareResponse;
    }

    protected Set<NailsCareResponse> nailsCareSetToNailsCareResponseSet(Set<NailsCare> set) {
        if ( set == null ) {
            return null;
        }

        Set<NailsCareResponse> set1 = new LinkedHashSet<NailsCareResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( NailsCare nailsCare : set ) {
            set1.add( nailsCareToNailsCareResponse( nailsCare ) );
        }

        return set1;
    }
}
