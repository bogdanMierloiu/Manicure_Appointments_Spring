package ro.musiclover.manicureappointments.model.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
public class DateBetweenRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future(message = "Please check the date")
    private LocalDateTime dateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Future(message = "Please check the date")
    private LocalDateTime dateTo;


}