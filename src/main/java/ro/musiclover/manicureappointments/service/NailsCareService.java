package ro.musiclover.manicureappointments.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.musiclover.manicureappointments.entity.NailsCare;
import ro.musiclover.manicureappointments.exception.BusinessException;
import ro.musiclover.manicureappointments.mapper.NailsServiceMapper;
import ro.musiclover.manicureappointments.model.nails_services.NailsCareResponse;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;
import ro.musiclover.manicureappointments.repository.MyRepository;
import ro.musiclover.manicureappointments.repository.NailsCareRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NailsCareService {

    private final NailsCareRepository nailsCareRepository;
    private final NailsServiceMapper nailsServiceMapper;
    private final MyRepository myRepository;


    public NailsCareResponse createService(NailsServiceRequest nailsServiceRequest) {
        checkDuplicate(nailsServiceRequest);
        NailsCare nailsCareToCreate = nailsServiceMapper.map(nailsServiceRequest);
        NailsCare nailsCareToResponse = nailsCareRepository.save(nailsCareToCreate);
        return nailsServiceMapper.map(nailsCareToResponse);
    }


    public List<NailsCareResponse> allServices() {
        return nailsServiceMapper.map(nailsCareRepository.findAll());
    }


    public NailsCareResponse findServiceByID(Integer id) {
        NailsCare nailsCare = nailsCareRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        return nailsServiceMapper.map(nailsCare);
    }


    public List<NailsCareResponse> findByServiceName(String name) {
        List<NailsCare> serviceListFromDB = myRepository.findByServiceName(name);
        return createListOfServiceForResponseFromDB(serviceListFromDB);
    }

    static List<NailsCareResponse> createListOfServiceForResponseFromDB(List<NailsCare> serviceListFromDB) {
        List<NailsCareResponse> serviceListForResponse = new ArrayList<>();
        for (NailsCare nailsCare : serviceListFromDB) {
            NailsCareResponse nailsCareResponse = new NailsCareResponse();
            nailsCareResponse.setId(nailsCare.getId());
            nailsCareResponse.setServiceName(nailsCare.getServiceName());
            nailsCareResponse.setPrice(nailsCare.getPrice());
            serviceListForResponse.add(nailsCareResponse);
        }
        return serviceListForResponse;
    }

    public void updateServicePrice(RequestUpdatePrice requestUpdatePrice) {
        NailsCare nailsCareToUpdate = nailsCareRepository.findById(requestUpdatePrice.getId()).orElseThrow(
                () -> new BusinessException(String.format(
                        "The service with id: %s not exist", requestUpdatePrice.getId()))
        );
        nailsCareToUpdate.setPrice(requestUpdatePrice.getPrice());
    }
    public void updateServiceName(RequestUpdateName requestUpdateName) {
        NailsCare nailsCareToUpdate = nailsCareRepository.findById(requestUpdateName.getId()).orElseThrow(
                () -> new BusinessException(String.format(
                        "The service with id: %s not exist", requestUpdateName.getId()))
        );
        nailsCareToUpdate.setServiceName(requestUpdateName.getServiceName());
    }
    public void deleteService(Integer id) {
        NailsCare nailsCareToDelete = nailsCareRepository.findById(id).orElseThrow(
                () -> new BusinessException(String.format("The service with id: %s not exist", id))
        );
        nailsCareRepository.delete(nailsCareToDelete);
    }
    public void checkDuplicate(NailsServiceRequest nailsServiceRequest) {
        for (NailsCare nailsCare : nailsCareRepository.findAll()) {
            if (nailsCare.getServiceName().equals(nailsServiceRequest.getServiceName())) {
                throw new BusinessException("This service already exist");
            }
        }
    }
}
