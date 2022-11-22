package ro.musiclover.manicureappointments.model.appointment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentRequest {

    private int id;

    @NotNull
    @Future(message = "Please check the date")
    private LocalDate appointmentDate;

    @NotNull
    private LocalTime appointmentTime;

    private Integer manicuristId;

    private Integer customerId;

    private int[] nailsServicesIds;

}