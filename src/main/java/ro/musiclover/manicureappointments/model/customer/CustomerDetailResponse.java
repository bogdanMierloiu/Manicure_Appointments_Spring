package ro.musiclover.manicureappointments.model.customer;
import lombok.*;
import ro.musiclover.manicureappointments.model.appointment.AppointmentResponseForCustomerDetail;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDetailResponse {

    private String firstName;
    private String lastName;
    private List<AppointmentResponseForCustomerDetail> appointments;

}