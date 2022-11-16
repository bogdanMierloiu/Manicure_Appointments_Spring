package ro.musiclover.manicureappointments.service.implementation;
import lombok.RequiredArgsConstructor;
import ro.musiclover.manicureappointments.service.interfaces.IBase;

import java.util.Optional;

@RequiredArgsConstructor

public class Base<T> implements IBase<T> {

    private final Class<T> entityClass;


    @Override
    public void checkFindById(Optional<T> entity) {
        if (entity.isEmpty()) {
            throw new IllegalArgumentException(
                    "The manicurist with id selected not found");
        }
    }

    @Override
    public void checkId(Integer id) {
        if (id <= 0 || !id.toString().matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid id !!");
        }
    }

    @Override
    public void validateInputStrings(String string) {
        if (string.isBlank()) {
            throw new IllegalArgumentException(String.format("The %s is invalid,string", string));
        }
    }

//    @Override
//    public void validatePhoneNumber(String string) {
//        if (string.isBlank() ||
//                !string.matches("[0-9]+") ||
//                string.length() < 10) {
//            throw new IllegalArgumentException("Invalid phone number");
//        }
//    }


}
