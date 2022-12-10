package ro.musiclover.manicureappointments.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;

import java.util.List;

@ComponentScan
@Mapper(componentModel = "spring")
public interface NailsServiceMapper {

    NailsCare map(NailsServiceRequest nailsServiceRequest);

    NailsServiceResponse map(NailsCare nailsCare);

    List<NailsServiceResponse> map(List<NailsCare> all);

    NailsCare map(RequestUpdateName requestUpdateName);

    NailsCare map(RequestUpdatePrice requestUpdatePrice);

}
