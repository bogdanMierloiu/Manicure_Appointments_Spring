package ro.musiclover.manicureappointments.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristRequest;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-01T15:38:50+0200",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ManicuristMapperImpl implements ManicuristMapper {

    @Override
    public ManicuristResponse map(Manicurist manicurist) {
        if ( manicurist == null ) {
            return null;
        }

        ManicuristResponse manicuristResponse = new ManicuristResponse();

        manicuristResponse.setId( manicurist.getId() );
        manicuristResponse.setFirstName( manicurist.getFirstName() );
        manicuristResponse.setLastName( manicurist.getLastName() );
        manicuristResponse.setPhoneNumber( manicurist.getPhoneNumber() );
        manicuristResponse.setHireDate( manicurist.getHireDate() );

        return manicuristResponse;
    }

    @Override
    public Manicurist map(ManicuristRequest manicuristRequest) {
        if ( manicuristRequest == null ) {
            return null;
        }

        Manicurist.ManicuristBuilder manicurist = Manicurist.builder();

        manicurist.id( manicuristRequest.getId() );
        manicurist.firstName( manicuristRequest.getFirstName() );
        manicurist.lastName( manicuristRequest.getLastName() );
        manicurist.phoneNumber( manicuristRequest.getPhoneNumber() );
        manicurist.hireDate( manicuristRequest.getHireDate() );

        return manicurist.build();
    }

    @Override
    public List<ManicuristResponse> map(List<Manicurist> all) {
        if ( all == null ) {
            return null;
        }

        List<ManicuristResponse> list = new ArrayList<ManicuristResponse>( all.size() );
        for ( Manicurist manicurist : all ) {
            list.add( map( manicurist ) );
        }

        return list;
    }
}
