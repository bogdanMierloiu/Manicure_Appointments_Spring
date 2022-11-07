package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.repository.ManicuristRepository;
import ro.musiclover.manicureappointments.service.interfaces.IManicurist;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManicuristService implements IManicurist {

    private final ManicuristRepository manicuristRepository;

    @Override
    public void createManicurist(Manicurist manicurist) {
        if (manicurist.getFirstName().isBlank()) {
            throw new IllegalArgumentException("The first name is invalid");
        }
        if (manicurist.getLastName().isBlank()) {
            throw new IllegalArgumentException("The last name is invalid");
        }
        if (manicurist.getPhoneNumber().isBlank() ||
                !manicurist.getPhoneNumber().matches("[0-9]+") ||
                manicurist.getPhoneNumber().length() < 10) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (manicurist.getHireDate().after(Date.valueOf(
                LocalDate.now().plus(1, ChronoUnit.DAYS))) ||
                manicurist.getHireDate().toString().isBlank()) {
            throw new DateTimeException("Invalid date");
        }
        manicuristRepository.save(manicurist);
    }

    @Override
    public List<Manicurist> allManicurists() {
        return manicuristRepository.findAll();
    }

    @Override
    public Optional<Manicurist> findManicuristById(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id !!");
        }
        Optional<Manicurist> optionalManicurist = manicuristRepository.findById(id);
        if (optionalManicurist.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("The manicurist with id:%s not found", id));
        }
        return optionalManicurist;
    }

    @Override
    public void updateManicurist(Integer id, Manicurist manicurist) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id !!");
        }
        Optional<Manicurist> manicuristToUpdate = manicuristRepository.findById(id);
        if (manicuristToUpdate.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("The manicurist with id:%s not found", id));
        }
        manicuristToUpdate.get().setFirstName(manicurist.getFirstName());
        manicuristToUpdate.get().setLastName(manicurist.getLastName());
        manicuristToUpdate.get().setPhoneNumber(manicurist.getPhoneNumber());
        manicuristToUpdate.get().setHireDate(manicurist.getHireDate());
        manicuristRepository.save(manicuristToUpdate.get());

    }

    @Override
    public void deleteManicurist(Integer id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id !!");
        }
        Optional<Manicurist> manicuristToDelete = manicuristRepository.findById(id);
        if (manicuristToDelete.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("The manicurist with id:%s not found", id));
        }
        manicuristRepository.delete(manicuristToDelete.get());

    }


}
