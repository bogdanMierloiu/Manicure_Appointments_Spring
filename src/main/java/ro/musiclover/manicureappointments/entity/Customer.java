package ro.musiclover.manicureappointments.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Audited
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @Past
    private LocalDate birthDate;

    @NotBlank
    private String email;

    private Boolean active;


    @OneToMany(cascade = {CascadeType.ALL},
            mappedBy = "customer")
    private List<Appointment> appointments;
}