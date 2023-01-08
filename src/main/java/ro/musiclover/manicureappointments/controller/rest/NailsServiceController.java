package ro.musiclover.manicureappointments.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.musiclover.manicureappointments.model.nails_services.NailsCareResponse;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;
import ro.musiclover.manicureappointments.service.NailsCareService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("nails-service")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Validated
public class NailsServiceController {
    private final NailsCareService nailsCareService;

    @PostMapping("create")
    public NailsCareResponse createNailsService(@RequestBody @Valid NailsServiceRequest nailsServiceRequest) {
        return nailsCareService.createService(nailsServiceRequest);
    }

    @GetMapping("list")
    public List<NailsCareResponse> showAllServices() {
        return nailsCareService.allServices();
    }

    @GetMapping("find/{id}")
    public NailsCareResponse findById(@PathVariable Integer id) {
        return nailsCareService.findServiceByID(id);
    }

    @GetMapping("search/{name}")
    public List<NailsCareResponse> findByServiceName(@PathVariable String name){
        return nailsCareService.findByServiceName(name);
    }
    @PatchMapping("update-price")
    public void updateServicePrice(@RequestBody @Valid RequestUpdatePrice requestUpdatePrice) {
        nailsCareService.updateServicePrice(requestUpdatePrice);
    }

    @PatchMapping("update-name")
    public void updateServiceName(@RequestBody @Valid RequestUpdateName requestUpdateName) {
        nailsCareService.updateServiceName(requestUpdateName);
    }

    @DeleteMapping("delete/{id}")
    public void deleteService(@PathVariable Integer id) {
        nailsCareService.deleteService(id);
    }


}
