package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceResponse;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;

import java.util.List;

public interface INailsService {

    NailsServiceResponse createService(NailsServiceRequest nailsServiceRequest);

    List<NailsServiceResponse> allServices();

    NailsServiceResponse findServiceByID(Integer id);

    void updateServicePrice(Integer id, RequestUpdatePrice requestUpdatePrice);

    void updateServiceName(Integer id, RequestUpdateName requestUpdateName);

    void deleteService(Integer id);

}
