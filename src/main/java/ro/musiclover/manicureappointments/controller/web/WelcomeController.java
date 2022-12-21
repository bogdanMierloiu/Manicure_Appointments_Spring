package ro.musiclover.manicureappointments.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.model.appointment.DateBetweenRequest;

import java.time.LocalDateTime;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String gotoWelcomePage() {
        return "welcomePage";
    }


}