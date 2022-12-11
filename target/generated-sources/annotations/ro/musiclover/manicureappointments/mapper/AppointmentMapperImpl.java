package ro.musiclover.manicureappointments.mapper;

import java.sql.Date;
import java.util.ArrayList;
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

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-11T10:41:52+0200",
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
        if ( appointmentRequest.getAppointmentDate() != null ) {
            appointment.appointmentDate( new Date( appointmentRequest.getAppointmentDate().getTime() ) );
        }
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
        if ( appointmentResponse.getNailsCares() != null ) {
            Set<NailsCare> set = appointment.getNailsCares();
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
}
