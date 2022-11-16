package ro.musiclover.manicureappointments.model.manicurist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ro.musiclover.manicureappointments.entity.Appointment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
public class ManicuristRequest {

    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @Past(message = "Hire date must be less than today")
    private Date hireDate;
}
