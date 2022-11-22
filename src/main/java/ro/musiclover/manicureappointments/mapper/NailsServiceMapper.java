package ro.musiclover.manicureappointments.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;

import java.util.List;

@ComponentScan
@Mapper(componentModel = "spring")
public interface NailsServiceMapper {

    NailsService map(NailsServiceRequest nailsServiceRequest);

    NailsServiceResponse map(NailsService nailsService);

    List<NailsServiceResponse> map(List<NailsService> all);

    NailsService map(RequestUpdateName requestUpdateName);

    NailsService map(RequestUpdatePrice requestUpdatePrice);

}
