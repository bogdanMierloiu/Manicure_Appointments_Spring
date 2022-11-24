package ro.musiclover.manicureappointments.model.nails_services;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class NailsServiceResponse {

    private Integer id;
    private String serviceName;
    private Integer price;

}
