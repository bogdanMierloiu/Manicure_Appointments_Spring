package ro.musiclover.manicureappointments.model.appointment;
import lombok.Data;
import javax.validation.constraints.Future;
import java.util.Date;

@Data
public class FindByDateRequest {

    @Future(message = "Please check the date")
    private Date appointmentDate;

}