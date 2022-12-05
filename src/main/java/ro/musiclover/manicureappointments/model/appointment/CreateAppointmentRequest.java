package ro.musiclover.manicureappointments.model.appointment;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateAppointmentRequest {

    private Date appointmentDate;
    private LocalTime appointmentTime;

    private Integer manicuristId;
    private Integer customerId;
    private int[] nailsServicesIds;

}