package ro.musiclover.manicureappointments.model.appointment;

import lombok.Data;
import ro.musiclover.manicureappointments.entity.Customer;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceForCustomerDetail;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
public class AppointmentResponseForCustomerDetail {

    private Date appointmentDate;

    private LocalTime appointmentTime;

    private List<NailsServiceForCustomerDetail> nailsServices;


}