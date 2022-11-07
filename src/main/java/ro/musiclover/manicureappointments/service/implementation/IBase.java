package ro.musiclover.manicureappointments.service.implementation;

import java.util.Optional;

public interface IBase<T> {

    void checkFindById(Optional<T> entity);

    void checkId(Integer id);

}
