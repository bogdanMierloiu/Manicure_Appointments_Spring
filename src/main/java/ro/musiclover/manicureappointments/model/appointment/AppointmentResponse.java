package ro.musiclover.manicureappointments.model.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.customer.CustomerResponseForAppointment;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponseForAppointment;
import ro.musiclover.manicureappointments.model.nails_services.NailsCareResponse;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AppointmentResponse {
    private Integer id;
    private LocalDateTime appointmentDateTime;
    private ManicuristResponseForAppointment manicurist;
    private CustomerResponseForAppointment customer;
    private final Set<NailsCareResponse> nailsCares = new HashSet<>();

}