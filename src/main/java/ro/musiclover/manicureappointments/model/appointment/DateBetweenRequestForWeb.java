package ro.musiclover.manicureappointments.model.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateBetweenRequestForWeb {


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Please check the date")
    private LocalDate dateFrom;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Please check the date")
    private LocalDate dateTo;


}