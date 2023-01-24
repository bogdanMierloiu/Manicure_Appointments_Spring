package ro.musiclover.manicureappointments.model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    private Integer id;

    @NotBlank(message = "Invalid first name")
    private String firstName;

    @NotBlank(message = "Invalid last name")
    private String lastName;

    @NotBlank(message = "Invalid phone number")
    private String phoneNumber;

    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @NotBlank
    private String email;

    private Boolean active = true;
}