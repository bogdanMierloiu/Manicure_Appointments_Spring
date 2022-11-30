package ro.musiclover.manicureappointments.controller.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String gotoLoginPage() {
        return "welcomePage";
    }
}
