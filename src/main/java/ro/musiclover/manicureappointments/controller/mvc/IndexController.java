package ro.musiclover.manicureappointments.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.entity.User;
import ro.musiclover.manicureappointments.service.UserService;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;

    @GetMapping("")
    public String viewIndexPage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.createUser(user);
        return "register_success";
    }
    @GetMapping("/goToApplication")
    public String goToApplication(){
        return "welcomePage";
    }

    @GetMapping("/welcome")
    public String gotoWelcomePage() {
        return "welcomePage";
    }
}
