package ro.musiclover.manicureappointments.model.customer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private boolean active;

}