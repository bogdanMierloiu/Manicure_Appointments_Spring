package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-01T15:38:49+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class NailsServiceMapperImpl implements NailsServiceMapper {

    @Override
    public NailsService map(NailsServiceRequest nailsServiceRequest) {
        if ( nailsServiceRequest == null ) {
            return null;
        }

        NailsService.NailsServiceBuilder nailsService = NailsService.builder();

        nailsService.id( nailsServiceRequest.getId() );
        nailsService.serviceName( nailsServiceRequest.getServiceName() );
        nailsService.price( nailsServiceRequest.getPrice() );

        return nailsService.build();
    }

    @Override
    public NailsServiceResponse map(NailsService nailsService) {
        if ( nailsService == null ) {
            return null;
        }

        NailsServiceResponse nailsServiceResponse = new NailsServiceResponse();

        nailsServiceResponse.setId( nailsService.getId() );
        nailsServiceResponse.setServiceName( nailsService.getServiceName() );
        nailsServiceResponse.setPrice( nailsService.getPrice() );

        return nailsServiceResponse;
    }

    @Override
    public List<NailsServiceResponse> map(List<NailsService> all) {
        if ( all == null ) {
            return null;
        }

        List<NailsServiceResponse> list = new ArrayList<NailsServiceResponse>( all.size() );
        for ( NailsService nailsService : all ) {
            list.add( map( nailsService ) );
        }

        return list;
    }

    @Override
    public NailsService map(RequestUpdateName requestUpdateName) {
        if ( requestUpdateName == null ) {
            return null;
        }

        NailsService.NailsServiceBuilder nailsService = NailsService.builder();

        nailsService.id( requestUpdateName.getId() );
        nailsService.serviceName( requestUpdateName.getServiceName() );

        return nailsService.build();
    }

    @Override
    public NailsService map(RequestUpdatePrice requestUpdatePrice) {
        if ( requestUpdatePrice == null ) {
            return null;
        }

        NailsService.NailsServiceBuilder nailsService = NailsService.builder();

        nailsService.id( requestUpdatePrice.getId() );
        nailsService.price( requestUpdatePrice.getPrice() );

        return nailsService.build();
    }
}
