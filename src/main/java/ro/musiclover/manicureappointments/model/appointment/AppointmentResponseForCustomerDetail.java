package ro.musiclover.manicureappointments.model.appointment;

import lombok.Data;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceForCustomerDetail;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@Data
public class AppointmentResponseForCustomerDetail implements Comparable<AppointmentResponseForCustomerDetail>{

    private Date appointmentDate;

    private LocalTime appointmentTime;

    private List<NailsServiceForCustomerDetail> nailsServices;


    @Override
    public int compareTo(AppointmentResponseForCustomerDetail o) {
        return getAppointmentDate().compareTo(o.appointmentDate);
    }
}