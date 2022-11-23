package ro.musiclover.manicureappointments.model.appointment;
import lombok.Data;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequest {

    private Integer id;

    @NotNull
    @Future(message = "Please check the date")
    private LocalDate appointmentDate;

    @NotNull
    private LocalTime appointmentTime;

    private Integer manicuristId;

    private Integer customerId;

    private int[] nailsServicesIds;

}