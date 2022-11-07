package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.entity.Manicurist;

import java.util.List;
import java.util.Optional;

public interface IManicurist {
    void createManicurist(Manicurist manicurist);

    List<Manicurist> allManicurists();

    Optional<Manicurist> findManicuristById(Integer id);

    void updateManicurist(Integer id, Manicurist manicurist);

    void deleteManicurist(Integer id);

}
