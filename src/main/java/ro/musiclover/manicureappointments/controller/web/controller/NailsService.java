package ro.musiclover.manicureappointments.controller.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NailsService {
    @GetMapping("/nails-service")
    public String goToNailsServicesPage(){
        return "nailsServicePage";
    }
}
