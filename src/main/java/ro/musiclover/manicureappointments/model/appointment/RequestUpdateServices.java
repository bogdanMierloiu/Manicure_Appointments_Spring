package ro.musiclover.manicureappointments.model.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestUpdateServices {


    private Integer id;
    private int[] nailsServicesIds;

}