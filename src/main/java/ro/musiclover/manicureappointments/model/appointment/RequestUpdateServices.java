package ro.musiclover.manicureappointments.model.appointment;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class RequestUpdateServices {


    private Integer id;
    private int[] nailsServicesIds;

}