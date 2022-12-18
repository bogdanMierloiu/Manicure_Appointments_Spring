package ro.musiclover.manicureappointments.model.appointment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.customer.CustomerResponseForAppointment;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponseForAppointment;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data

public class AppointmentResponse {
    private Integer id;
    private LocalDateTime appointmentDateTime;
    private ManicuristResponseForAppointment manicurist;
    private CustomerResponseForAppointment customer;
    @JsonIgnore
    private final Set<NailsCare> nailsCares = new HashSet<>();


}