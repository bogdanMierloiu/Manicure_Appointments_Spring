package ro.musiclover.manicureappointments.controller.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.model.DeleteRequest;
import ro.musiclover.manicureappointments.service.implementation.NailsServiceService;

@RequiredArgsConstructor
@Controller
public class NailsServiceWebController {

    private final NailsServiceService nailsService;

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
    public String deleteService(@ModelAttribute(value = "deleteRequest") DeleteRequest request, Model model) {
        nailsService.deleteService(request.getId());
        model.addAttribute("services", nailsService.allServices());
        return "allNailsServicesPage";
    }
}
