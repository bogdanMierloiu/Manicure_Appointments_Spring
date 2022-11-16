package ro.musiclover.manicureappointments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class NailsService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @NotBlank
    private String serviceName;

    @NotBlank
    private Integer price;

    @ManyToMany(mappedBy = "nailsServices")
    private List<Appointment> appointments = new ArrayList<>();
}
