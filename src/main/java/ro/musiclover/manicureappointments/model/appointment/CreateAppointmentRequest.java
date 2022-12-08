package ro.musiclover.manicureappointments.model.appointment;

import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;

@Data
public class CreateAppointmentRequest {

    private Date appointmentDate;
    private LocalTime appointmentTime;

    private Integer manicuristId;
    private Integer customerId;
    private int[] nailsServicesIds;

}