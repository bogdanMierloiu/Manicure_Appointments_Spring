package ro.musiclover.manicureappointments.model.customer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;


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
    private LocalDate birthDate;

    @NotBlank
    private String email;

    private Boolean active;
}