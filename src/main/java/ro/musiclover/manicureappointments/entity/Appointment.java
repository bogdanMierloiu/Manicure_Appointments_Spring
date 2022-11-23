package ro.musiclover.manicureappointments.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
;

@Entity
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    @NotNull
    @Future(message = "Please check the date")
    private LocalDate appointmentDate;

    @NotNull
    private LocalTime appointmentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manicurist manicurist;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<NailsService> nailsServices = new ArrayList<>();

}