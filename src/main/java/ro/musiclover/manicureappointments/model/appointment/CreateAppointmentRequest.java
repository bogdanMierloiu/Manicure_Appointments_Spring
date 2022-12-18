package ro.musiclover.manicureappointments.model.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateAppointmentRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "Please check the date")
    private LocalDateTime appointmentDateTime;

    private Integer manicuristId;
    private Integer customerId;
    private List<Integer> nailsServicesIds;

}