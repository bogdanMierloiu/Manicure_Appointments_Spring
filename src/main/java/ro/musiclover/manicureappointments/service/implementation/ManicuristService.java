package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.manicurist.ManicuristMapper;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristRequest;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponse;
import ro.musiclover.manicureappointments.repository.ManicuristRepository;
import ro.musiclover.manicureappointments.service.interfaces.IManicurist;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ManicuristService implements IManicurist {

    private final ManicuristRepository manicuristRepository;
    private final ManicuristMapper manicuristMapper;


    @Override
    public ManicuristResponse createManicurist(ManicuristRequest manicuristRequest) {
        validatePhoneNumber(manicuristRequest.getPhoneNumber());
        Manicurist manicurist = manicuristMapper.map(manicuristRequest);
        return manicuristMapper.map(manicuristRepository.save(manicurist));
    }

    @Override
    public List<ManicuristResponse> allManicurists() {
        return manicuristMapper.map(manicuristRepository.findAll());

    }

    @Override
    public ManicuristResponse findManicuristById(Integer id) {
        Manicurist manicurist = manicuristRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("The manicurist with id: %s not exist", id))
        );
        return manicuristMapper.map(manicurist);
    }

    @Override
    public void updateManicurist(Integer id, ManicuristRequest manicuristRequest) {
        validatePhoneNumber(manicuristRequest.getPhoneNumber());
        Manicurist manicuristToUpdate = manicuristRepository.findById(id).orElseThrow(
                () -> new BusinessException(
                        String.format("The manicurist with id: %s not exist", id)
                )
        );
        manicuristToUpdate.setFirstName(manicuristRequest.getFirstName());
        manicuristToUpdate.setLastName(manicuristRequest.getLastName());
        manicuristToUpdate.setPhoneNumber(manicuristRequest.getPhoneNumber());
        manicuristToUpdate.setHireDate(manicuristRequest.getHireDate());
        manicuristMapper.map(manicuristRepository.save(manicuristToUpdate));
    }

    @Override
    public void deleteManicurist(Integer id) {
//        checkId(id);
        Optional<Manicurist> manicuristToDelete = manicuristRepository.findById(id);
//        checkFindById(manicuristToDelete);
        manicuristRepository.delete(manicuristToDelete.get());

    }


    @Override
    public void validatePhoneNumber(String string) {
        if (string.isBlank() ||
                !string.matches("[0-9]+") ||
                string.length() < 10) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }


}
