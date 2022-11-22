package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.model.appointment.AppointmentRequest;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponse;

public interface IAppointment {

    AppointmentResponse createAppointment(AppointmentRequest appointmentRequest);
}
