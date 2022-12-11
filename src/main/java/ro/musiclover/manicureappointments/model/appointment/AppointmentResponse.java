package ro.musiclover.manicureappointments.model.appointment;
import lombok.Data;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.customer.CustomerResponseForAppointment;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponseForAppointment;
import ro.musiclover.manicureappointments.model.nails_services.NailsCareResponse;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
public class AppointmentResponse {

    private Integer id;
    private Date appointmentDate;
    private LocalTime appointmentTime;
    private ManicuristResponseForAppointment manicurist;
    private CustomerResponseForAppointment customer;
    private final Set<NailsCare> nailsCares = new HashSet<>();



}