package ro.musiclover.manicureappointments.model.customer;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class CustomerRequest {

    private Integer id;

    @NotBlank(message = "Invalid first name")
    private String firstName;

    @NotBlank(message = "Invalid last name")
    private String lastName;

    @NotBlank(message = "Invalid phone number")
    private String phoneNumber;
}