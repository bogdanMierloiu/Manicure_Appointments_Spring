package ro.musiclover.manicureappointments.model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Past;
import java.sql.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateBirthDateCustomer {

    private Integer id;
    @Past
    private Date birthDate;


}