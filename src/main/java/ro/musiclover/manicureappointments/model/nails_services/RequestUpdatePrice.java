package ro.musiclover.manicureappointments.model.nails_services;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class RequestUpdatePrice {

    private Integer id;

    @NotNull
    @Positive
    private Integer price;
}
