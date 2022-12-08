package ro.musiclover.manicureappointments.model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateFirstNameCustomer {

    private Integer id;

    @NotBlank(message = "Invalid first name")
    private String firstName;

   }