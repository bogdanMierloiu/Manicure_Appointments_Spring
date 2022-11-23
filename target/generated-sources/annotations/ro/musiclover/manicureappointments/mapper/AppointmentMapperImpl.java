package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-23T17:39:44+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public Appointment map(AppointmentRequest appointmentRequest) {
        if ( appointmentRequest == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setId( appointmentRequest.getId() );
        appointment.setAppointmentDate( appointmentRequest.getAppointmentDate() );
        appointment.setAppointmentTime( appointmentRequest.getAppointmentTime() );

        return appointment;
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
}
