package ro.musiclover.manicureappointments.model.appointment;

import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RequestUpdateDate {

    private Integer id;

    @NotNull
    @Future(message = "Please check the date")
    private LocalDate appointmentDate;


}