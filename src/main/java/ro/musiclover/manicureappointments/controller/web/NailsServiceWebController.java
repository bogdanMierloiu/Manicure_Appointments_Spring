package ro.musiclover.manicureappointments.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.model.IdRequest;
import ro.musiclover.manicureappointments.model.nails_services.CreateNailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.NailsServiceRequest;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdateName;
import ro.musiclover.manicureappointments.model.nails_services.RequestUpdatePrice;
import ro.musiclover.manicureappointments.service.implementation.NailsCareService;

@RequiredArgsConstructor
@Controller
public class NailsServiceWebController {

    private final NailsCareService nailsService;

    @GetMapping("/nails-service")
    public String goToNailsServicesPage() {
        return "nailsServicePage";
    }

    @GetMapping("/nails-service/allServices")
    public String goToAllNailsServices(Model model) {
        model.addAttribute("services", nailsService.allServices());
        return "allNailsServicesPage";
    }

    @PostMapping("/nails-service/delete")
    public String deleteService(@ModelAttribute(value = "deleteRequest") IdRequest request, Model model) {
        nailsService.deleteService(request.getId());
        model.addAttribute("services", nailsService.allServices());
        return "allNailsServicesPage";
    }

    @PostMapping("nails-service/update-name")
    public String updateServiceName(@ModelAttribute(value = "updateNameRequest") RequestUpdateName request, Model model) {
        nailsService.updateServiceName(request.getId(), request);
        model.addAttribute("services", nailsService.allServices());
        return "allNailsServicesPage";
    }

    @PostMapping("nails-service/update-price")
    public String updateServicePrice(@ModelAttribute(value = "updatePriceRequest") RequestUpdatePrice request, Model model) {
        nailsService.updateServicePrice(request.getId(), request);
        model.addAttribute("services", nailsService.allServices());
        return "allNailsServicesPage";
    }

    @GetMapping("/nails-service/goToCreateServicePage")
    public String goToCreateServicePage() {
        return "createServicePage";
    }

    @PostMapping("/nails-service/createService")
    public String createService(@ModelAttribute(value = "createServiceRequest") CreateNailsServiceRequest request,
                                Model model) {
        NailsServiceRequest nailsServiceRequest = NailsServiceRequest.builder()
                .serviceName(request.getServiceName())
                .price(request.getPrice())
                .build();
        nailsService.createService(nailsServiceRequest);
        model.addAttribute("services", nailsService.allServices());
        return "allNailsServicesPage";
    }
}
