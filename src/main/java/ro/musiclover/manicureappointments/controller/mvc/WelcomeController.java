package ro.musiclover.manicureappointments.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String gotoWelcomePage() {
        return "welcomePage";
    }


}