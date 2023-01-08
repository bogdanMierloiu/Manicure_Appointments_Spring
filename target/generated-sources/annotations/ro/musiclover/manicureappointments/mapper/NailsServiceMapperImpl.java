package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.model.nails_services.NailsCareResponse;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-04T19:15:41+0200",
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
    public NailsCareResponse map(NailsCare nailsCare) {
        if ( nailsCare == null ) {
            return null;
        }

        NailsCareResponse nailsCareResponse = new NailsCareResponse();

        nailsCareResponse.setId( nailsCare.getId() );
        nailsCareResponse.setServiceName( nailsCare.getServiceName() );
        nailsCareResponse.setPrice( nailsCare.getPrice() );

        return nailsCareResponse;
    }

    @Override
    public List<NailsCareResponse> map(List<NailsCare> all) {
        if ( all == null ) {
            return null;
        }

        List<NailsCareResponse> list = new ArrayList<NailsCareResponse>( all.size() );
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
