package ro.musiclover.manicureappointments.model.customer;
import lombok.Data;
import java.util.Date;

@Data
public class CustomerResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date birthDate;
    private String email;
    private Boolean active;

}