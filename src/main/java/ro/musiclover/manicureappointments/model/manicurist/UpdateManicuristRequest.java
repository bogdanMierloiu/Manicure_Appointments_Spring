package ro.musiclover.manicureappointments.model.manicurist;

import lombok.Data;

import java.sql.Date;

@Data
public class UpdateManicuristRequest {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date hireDate;

}
