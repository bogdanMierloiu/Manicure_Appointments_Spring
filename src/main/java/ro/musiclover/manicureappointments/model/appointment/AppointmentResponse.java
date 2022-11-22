package ro.musiclover.manicureappointments.model.appointment;

import lombok.Getter;
import lombok.Setter;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.entity.NailsService;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AppointmentResponse {

    private int id;

    @NotNull
    @Future(message = "Please check the date")
    private LocalDate appointmentDate;

    @NotNull
    private LocalTime appointmentTime;

    private Integer manicuristId;

    private Integer customerId;

    private int[] nailsServicesIds;

}