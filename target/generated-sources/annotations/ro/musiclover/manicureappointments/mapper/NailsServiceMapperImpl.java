package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-10T16:18:39+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class NailsServiceMapperImpl implements NailsServiceMapper {

    @Override
    public NailsCare map(NailsServiceRequest nailsServiceRequest) {
        if ( nailsServiceRequest == null ) {
            return null;
        }

        NailsCare.NailsCareBuilder nailsCare = NailsCare.builder();

        nailsCare.id( nailsServiceRequest.getId() );
        nailsCare.serviceName( nailsServiceRequest.getServiceName() );
        nailsCare.price( nailsServiceRequest.getPrice() );

        return nailsCare.build();
    }

    @Override
    public NailsServiceResponse map(NailsCare nailsCare) {
        if ( nailsCare == null ) {
            return null;
        }

        NailsServiceResponse nailsServiceResponse = new NailsServiceResponse();

        nailsServiceResponse.setId( nailsCare.getId() );
        nailsServiceResponse.setServiceName( nailsCare.getServiceName() );
        nailsServiceResponse.setPrice( nailsCare.getPrice() );

        return nailsServiceResponse;
    }

    @Override
    public List<NailsServiceResponse> map(List<NailsCare> all) {
        if ( all == null ) {
            return null;
        }

        List<NailsServiceResponse> list = new ArrayList<NailsServiceResponse>( all.size() );
        for ( NailsCare nailsCare : all ) {
            list.add( map( nailsCare ) );
        }

        return list;
    }

    @Override
    public NailsCare map(RequestUpdateName requestUpdateName) {
        if ( requestUpdateName == null ) {
            return null;
        }

        NailsCare.NailsCareBuilder nailsCare = NailsCare.builder();

        nailsCare.id( requestUpdateName.getId() );
        nailsCare.serviceName( requestUpdateName.getServiceName() );

        return nailsCare.build();
    }

    @Override
    public NailsCare map(RequestUpdatePrice requestUpdatePrice) {
        if ( requestUpdatePrice == null ) {
            return null;
        }

        NailsCare.NailsCareBuilder nailsCare = NailsCare.builder();

        nailsCare.id( requestUpdatePrice.getId() );
        nailsCare.price( requestUpdatePrice.getPrice() );

        return nailsCare.build();
    }
}
