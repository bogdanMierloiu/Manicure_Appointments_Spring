package ro.musiclover.manicureappointments.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Manicurist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date hireDate;

    @OneToMany(mappedBy = "manicurist")
    @JsonIgnore
    private List<Appointment> appointments;
}
