package ro.musiclover.manicureappointments.model.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateServices {


    private Integer id;
    private int[] nailsServicesIds;

}