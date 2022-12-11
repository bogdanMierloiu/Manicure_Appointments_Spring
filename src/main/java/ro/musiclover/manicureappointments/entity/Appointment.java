package ro.musiclover.manicureappointments.entity;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Future(message = "Please check the date")
    private Date appointmentDate;

    @NotNull
    private LocalTime appointmentTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manicurist manicurist;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "appointment_services",
            joinColumns = {@JoinColumn(name = "appointment_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id", referencedColumnName = "id")})
    @Builder.Default
    private Set<NailsCare> nailsCares = new HashSet<>();


}