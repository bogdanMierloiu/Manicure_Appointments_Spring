package ro.musiclover.manicureappointments.model.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentRequest {

    private Integer id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Please check the date")
    private LocalDateTime appointmentDateTime;

    private Integer manicuristId;

    private Integer customerId;

    private List<Integer> nailsServicesIds;

}