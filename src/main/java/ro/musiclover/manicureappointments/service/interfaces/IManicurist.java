package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristRequest;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponse;

import java.util.List;
import java.util.Optional;

public interface IManicurist {
    ManicuristResponse createManicurist(ManicuristRequest manicuristRequest);

    List<ManicuristResponse> allManicurists();

    ManicuristResponse findManicuristById(Integer id);

    void updateManicurist(Integer id, ManicuristRequest manicuristRequest);

    void deleteManicurist(Integer id);

    void validatePhoneNumber(String string);

}
