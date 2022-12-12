package ro.musiclover.manicureappointments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.entity.User;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.ManicuristMapper;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristRequest;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponse;
import ro.musiclover.manicureappointments.repository.ManicuristRepository;
import ro.musiclover.manicureappointments.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;


    public void createUser(User user) {
        userRepository.save(user);
    }


    public void validatePhoneNumber(String string) {
        if (string.isBlank() ||
                !string.matches("[0-9]+") ||
                string.length() < 10) {
            throw new BusinessException("Invalid phone number. Try again -> only with digits and minimum 10");
        }
    }
}
