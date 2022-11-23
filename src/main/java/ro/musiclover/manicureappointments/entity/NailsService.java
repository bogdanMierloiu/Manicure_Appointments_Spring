package ro.musiclover.manicureappointments.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @NotNull
    @Positive
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Appointment appointment;
}
