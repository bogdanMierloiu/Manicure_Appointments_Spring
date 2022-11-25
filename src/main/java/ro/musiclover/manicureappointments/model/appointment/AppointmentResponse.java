package ro.musiclover.manicureappointments.model.appointment;
import lombok.Data;
import ro.musiclover.manicureappointments.model.customer.CustomerResponseForAppointment;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponseForAppointment;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class AppointmentResponse {

    private Integer id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private ManicuristResponseForAppointment manicurist;
    private CustomerResponseForAppointment customer;
    private final List<NailsServiceResponse> nailsServices = new ArrayList<>();


}