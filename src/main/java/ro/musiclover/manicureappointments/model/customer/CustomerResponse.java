package ro.musiclover.manicureappointments.model.customer;
import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
public class CustomerResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}