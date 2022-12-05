package ro.musiclover.manicureappointments.model.nails_services;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateNailsServiceRequest {

    @NotBlank
    private String serviceName;
    @NotNull
    @Positive
    private Integer price;

}
