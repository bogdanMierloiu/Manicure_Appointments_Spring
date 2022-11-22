package ro.musiclover.manicureappointments.service.implementation;
import lombok.RequiredArgsConstructor;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.service.interfaces.IBase;

@RequiredArgsConstructor

public class Base<T> implements IBase<T> {
    @Override
    public void validatePhoneNumber(String string) {
        if (string.isBlank() ||
                !string.matches("[0-9]+") ||
                string.length() < 10) {
            throw new BusinessException("Invalid phone number. Try again -> only with digits and minimum 10");
        }
    }


}
