package ro.musiclover.manicureappointments.model.manicurist;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;

@Data
public class UpdateManicuristRequest {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "Hire date must be less than today")
    private LocalDate hireDate;

}
