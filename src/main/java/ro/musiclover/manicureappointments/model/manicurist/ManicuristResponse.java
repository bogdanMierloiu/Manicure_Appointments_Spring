package ro.musiclover.manicureappointments.model.manicurist;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ManicuristResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate hireDate;

}
