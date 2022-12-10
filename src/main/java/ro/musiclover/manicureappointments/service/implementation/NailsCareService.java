package ro.musiclover.manicureappointments.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.NailsServiceMapper;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;
import ro.musiclover.manicureappointments.repository.MyRepository;
import ro.musiclover.manicureappointments.repository.NailsServiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NailsCareService {

    private final NailsServiceRepository nailsServiceRepository;
    private final NailsServiceMapper nailsServiceMapper;
    private final MyRepository myRepository;


    public NailsServiceResponse createService(NailsServiceRequest nailsServiceRequest) {
        checkDuplicate(nailsServiceRequest);
        NailsCare nailsCareToCreate = nailsServiceMapper.map(nailsServiceRequest);
        NailsCare nailsCareToResponse = nailsServiceRepository.save(nailsCareToCreate);
        return nailsServiceMapper.map(nailsCareToResponse);
    }


    public List<NailsServiceResponse> allServices() {
        return nailsServiceMapper.map(nailsServiceRepository.findAll());
    }


    public NailsServiceResponse findServiceByID(Integer id) {
        NailsCare nailsCare = nailsServiceRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        return nailsServiceMapper.map(nailsCare);
    }


    public List<NailsServiceResponse> findByServiceName(String name) {
        List<NailsCare> serviceListFromDB = myRepository.findByServiceName(name);
        return createListOfServiceForResponseFromDB(serviceListFromDB);
    }

    static List<NailsServiceResponse> createListOfServiceForResponseFromDB(List<NailsCare> serviceListFromDB) {
        List<NailsServiceResponse> serviceListForResponse = new ArrayList<>();
        for (NailsCare nailsCare : serviceListFromDB) {
            NailsServiceResponse nailsServiceResponse = new NailsServiceResponse();
            nailsServiceResponse.setId(nailsCare.getId());
            nailsServiceResponse.setServiceName(nailsCare.getServiceName());
            nailsServiceResponse.setPrice(nailsCare.getPrice());
            serviceListForResponse.add(nailsServiceResponse);
        }
        return serviceListForResponse;
    }


    public void updateServicePrice(Integer id, RequestUpdatePrice requestUpdatePrice) {
        NailsCare nailsCareToUpdate = nailsServiceRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        nailsCareToUpdate.setPrice(requestUpdatePrice.getPrice());
    }


    public void updateServiceName(Integer id, RequestUpdateName requestUpdateName) {
        NailsCare nailsCareToUpdate = nailsServiceRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        nailsCareToUpdate.setServiceName(requestUpdateName.getServiceName());
    }

    public void deleteService(Integer id) {
        NailsCare nailsCareToDelete = nailsServiceRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        nailsServiceRepository.delete(nailsCareToDelete);
    }

    public void checkDuplicate(NailsServiceRequest nailsServiceRequest) {
        for (NailsCare nailsCare : nailsServiceRepository.findAll()) {
            if (nailsCare.getServiceName().equals(nailsServiceRequest.getServiceName())) {
                throw new BusinessException("This service already exist");
            }
        }
    }
}
