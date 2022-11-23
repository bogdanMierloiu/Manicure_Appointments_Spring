package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;

import java.util.List;

public interface IAppointment {

    AppointmentResponse createAppointment(AppointmentRequest appointmentRequest);

    AppointmentResponse findById(Integer id);

    List<AppointmentResponse> findAll();

    void updateAppointment(Integer id, AppointmentRequest appointmentRequest);

    void delete(Integer id);


}
