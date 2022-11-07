package ro.musiclover.manicureappointments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "nails_services")
public class NailsService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer id;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "price", nullable = false)
    private Integer price;

    @ManyToMany(mappedBy = "nailsServices")
    private List<Appointment> appointments = new ArrayList<>();
}
