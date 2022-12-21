package ro.musiclover.manicureappointments.model.customer;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateCustomerRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    private String email;

}