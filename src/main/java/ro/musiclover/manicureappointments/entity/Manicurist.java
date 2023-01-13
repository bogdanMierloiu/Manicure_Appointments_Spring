package ro.musiclover.manicureappointments.entity;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate hireDate;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "manicurist")
    private List<Appointment> appointments;
}
