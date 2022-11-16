package ro.musiclover.manicureappointments.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristRequest;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponse;

import java.util.List;

@ComponentScan
@Mapper(componentModel = "spring")
public interface ManicuristMapper {

    ManicuristResponse map(Manicurist manicurist);

    Manicurist map(ManicuristRequest manicuristRequest);

    List<ManicuristResponse> map(List<Manicurist> all);
}
