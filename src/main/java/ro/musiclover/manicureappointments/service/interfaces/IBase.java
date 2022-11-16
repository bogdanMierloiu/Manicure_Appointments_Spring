package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.entity.Manicurist;

import java.util.Optional;

public interface IBase<T> {

    void checkFindById(Optional<T> entity);

    void checkId(Integer id);

    void validateInputStrings(String string);

//    void validatePhoneNumber(String string);




}


