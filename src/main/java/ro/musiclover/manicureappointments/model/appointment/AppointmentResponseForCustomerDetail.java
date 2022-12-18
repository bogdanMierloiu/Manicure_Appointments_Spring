package ro.musiclover.manicureappointments.model.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceForCustomerDetail;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalTime;
import java.util.List;

@Data
public class AppointmentResponseForCustomerDetail{

    private LocalDateTime appointmentDateTime;

    private List<NailsServiceForCustomerDetail> nailsServices;


}