package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.NailsService;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.NailsServiceMapper;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;
import ro.musiclover.manicureappointments.repository.NailsServiceRepository;
import ro.musiclover.manicureappointments.service.interfaces.INailsService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NailsServiceService extends Base<NailsService> implements INailsService {

    private final NailsServiceRepository nailsServiceRepository;
    private final NailsServiceMapper nailsServiceMapper;

    @Override
    public NailsServiceResponse createService(NailsServiceRequest nailsServiceRequest) {
        checkDuplicate(nailsServiceRequest);
        NailsService nailsServiceToCreate = nailsServiceMapper.map(nailsServiceRequest);
        NailsService nailsServiceToResponse = nailsServiceRepository.save(nailsServiceToCreate);
        return nailsServiceMapper.map(nailsServiceToResponse);
    }

    @Override
    public List<NailsServiceResponse> allServices() {
        return nailsServiceMapper.map(nailsServiceRepository.findAll());
    }

    @Override
    public NailsServiceResponse findServiceByID(Integer id) {
        NailsService nailsService = nailsServiceRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        return nailsServiceMapper.map(nailsService);
    }

    @Override
    public void updateServicePrice(Integer id, RequestUpdatePrice requestUpdatePrice) {
        NailsService nailsServiceToUpdate = nailsServiceRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        nailsServiceToUpdate.setPrice(requestUpdatePrice.getPrice());
    }

    @Override
    public void updateServiceName(Integer id, RequestUpdateName requestUpdateName) {
        NailsService nailsServiceToUpdate = nailsServiceRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        nailsServiceToUpdate.setServiceName(requestUpdateName.getServiceName());
    }

    @Override
    public void deleteService(Integer id) {
        NailsService nailsServiceToDelete = nailsServiceRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        nailsServiceRepository.delete(nailsServiceToDelete);
    }

    public void checkDuplicate(NailsServiceRequest nailsServiceRequest) {
        for (NailsService nailsService : nailsServiceRepository.findAll()) {
            if (nailsService.getServiceName().equals(nailsServiceRequest.getServiceName())) {
                throw new BusinessException("This service already exist");
            }
        }
    }
}
