package ro.musiclover.manicureappointments.model.manicurist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ro.musiclover.manicureappointments.entity.Appointment;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Data
public class ManicuristResponse {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date hireDate;

}
