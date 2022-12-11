package ro.musiclover.manicureappointments.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.NailsCareResponse;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;

import java.util.List;

@ComponentScan
@Mapper(componentModel = "spring")
public interface NailsServiceMapper {

    NailsCare map(NailsServiceRequest nailsServiceRequest);

    NailsCareResponse map(NailsCare nailsCare);

    List<NailsCareResponse> map(List<NailsCare> all);

    NailsCare map(RequestUpdateName requestUpdateName);

    NailsCare map(RequestUpdatePrice requestUpdatePrice);

}
