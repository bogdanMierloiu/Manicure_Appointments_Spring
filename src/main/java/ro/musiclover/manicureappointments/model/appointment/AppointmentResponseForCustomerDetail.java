package ro.musiclover.manicureappointments.model.appointment;

import lombok.Data;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceForCustomerDetail;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AppointmentResponseForCustomerDetail {

    public Integer id;

    private LocalDateTime appointmentDateTime;

    private List<NailsServiceForCustomerDetail> nailsServices;


}