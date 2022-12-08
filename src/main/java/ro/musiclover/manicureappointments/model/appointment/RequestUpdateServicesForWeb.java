package ro.musiclover.manicureappointments.model.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class RequestUpdateServicesForWeb {


    private Integer id;
    private int[] nailsServicesIds;

}