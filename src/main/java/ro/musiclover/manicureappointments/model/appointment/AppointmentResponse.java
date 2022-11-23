package ro.musiclover.manicureappointments.model.appointment;
import lombok.Data;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.entity.NailsService;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class AppointmentResponse {

    private Integer id;

    @NotNull
    @Future(message = "Please check the date")
    private LocalDate appointmentDate;

    @NotNull
    private LocalTime appointmentTime;

    private Manicurist manicurist;

    private Customer customer;

    private List<NailsService> nailsServices ;


}