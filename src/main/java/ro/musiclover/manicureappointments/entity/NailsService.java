package ro.musiclover.manicureappointments.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NailsService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String serviceName;

    @NotNull
    @Positive
    private Integer price;

    @ManyToMany(mappedBy = "nailsServices")
    private List<Appointment> appointments = new ArrayList<>();
}
