package ro.musiclover.manicureappointments.model.nails_services;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestUpdateName {

    private Integer id;

    @NotBlank
    private String serviceName;

}
