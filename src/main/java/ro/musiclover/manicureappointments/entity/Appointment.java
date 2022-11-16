package ro.musiclover.manicureappointments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @NotBlank
    private LocalDate appointmentDate;

    @NotBlank
    private LocalTime appointmentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manicurist manicurist;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "appointment_services",
            joinColumns = {@JoinColumn(name = "appointment_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<NailsService> nailsServices = new ArrayList<>();

}