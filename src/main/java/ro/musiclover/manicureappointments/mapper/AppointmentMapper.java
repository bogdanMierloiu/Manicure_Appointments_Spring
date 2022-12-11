package ro.musiclover.manicureappointments.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;
import ro.musiclover.manicureappointments.entity.Appointment;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;
import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;

import java.util.List;

@ComponentScan
@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    Appointment map(AppointmentRequest appointmentRequest);

    AppointmentResponse map(Appointment appointment);

    List<AppointmentResponse> map(List<Appointment> all);

}
