package ro.musiclover.manicureappointments.entity;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    private boolean active;


    @OneToMany(cascade = {CascadeType.ALL},
            mappedBy = "customer")
    private List<Appointment> appointments;
}