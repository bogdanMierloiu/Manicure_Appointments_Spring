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
public class RequestUpdateEmailCustomer {

    private Integer id;
    @NotBlank
    private String email;

}