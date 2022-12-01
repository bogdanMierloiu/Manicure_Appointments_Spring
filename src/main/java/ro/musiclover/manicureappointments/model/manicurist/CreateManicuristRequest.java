package ro.musiclover.manicureappointments.model.manicurist;

import lombok.Data;

import java.sql.Date;
@Data
public class CreateManicuristRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date hireDate;

}
