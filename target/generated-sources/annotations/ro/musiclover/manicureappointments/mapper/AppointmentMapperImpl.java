package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-24T11:08:50+0200",
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
        appointmentResponse.setManicurist( appointment.getManicurist() );
        appointmentResponse.setCustomer( appointment.getCustomer() );
        Set<NailsService> set = appointment.getNailsServices();
        if ( set != null ) {
            appointmentResponse.setNailsServices( new ArrayList<NailsService>( set ) );
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
}
