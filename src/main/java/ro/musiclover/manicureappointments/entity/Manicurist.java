package ro.musiclover.manicureappointments.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Manicurist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @Past(message = "Hire date must be less than today")
    private Date hireDate;

    @OneToMany(mappedBy = "manicurist")
    private List<Appointment> appointments;
}
