package ro.musiclover.manicureappointments.model.appointment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class FindByDateRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Future(message = "Please check the date")
    private LocalDate appointmentDateTime;

}