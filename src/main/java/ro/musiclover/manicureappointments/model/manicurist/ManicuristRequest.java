package ro.musiclover.manicureappointments.model.manicurist;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class ManicuristRequest {

    private Integer id;

    @NotBlank(message = "Invalid first name")
    private String firstName;

    @NotBlank(message = "Invalid last name")
    private String lastName;

    @NotBlank(message = "Invalid last name")
    private String phoneNumber;

    @Past(message = "Hire date must be less than today")
    private Date hireDate;
}
