package ro.musiclover.manicureappointments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer id;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "appointment_time", nullable = false)
    private LocalTime appointmentTime;

    @ManyToOne
    @JoinColumn(name = "manicurist_id", nullable = false)
    private Manicurist manicurist;

    @ManyToOne
    @JoinColumn(name = "customers_id", nullable = false)
    private Customer customer;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "appointment_services",
            joinColumns = {@JoinColumn(name = "appointment_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<NailsService> nailsServices = new ArrayList<>();

}