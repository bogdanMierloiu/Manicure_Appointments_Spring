package ro.musiclover.manicureappointments.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.musiclover.manicureappointments.model.utils.UpdateRequest;
import ro.musiclover.manicureappointments.model.manicurist.CreateManicuristRequest;
import ro.musiclover.manicureappointments.model.utils.IdRequest;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristRequest;
import ro.musiclover.manicureappointments.model.manicurist.UpdateManicuristRequest;
import ro.musiclover.manicureappointments.service.ManicuristService;

@RequiredArgsConstructor
@Controller
public class ManicuristWebController {

    private final ManicuristService manicuristService;

    @GetMapping("/manicurist")
    public String goToManicuristPage() {
        return "manicuristPage";
    }

    @GetMapping("manicurist/allManicurists")
    public String goToAllManicuristList(Model model) {
        model.addAttribute("manicurists", manicuristService.allManicurists());
        return "allManicuristsPage";
    }

    @PostMapping("manicurist/delete")
    public String deleteManicurist(@ModelAttribute(value = "deleteRequest") IdRequest request, Model model) {
        manicuristService.deleteManicurist(request.getId());
        model.addAttribute("manicurists", manicuristService.allManicurists());
        return "allManicuristsPage";
    }

    @GetMapping("manicurist/goToUpdateManicuristPage")
    public String goToUpdateManicuristPage(@ModelAttribute(value = "request") IdRequest request, Model model) {
        model.addAttribute("manicurist", manicuristService.findManicuristById(request.getId()));
        return "updateManicuristPage";
    }

    @PostMapping("manicurist/update-manicurist")
    public String updateManicurist(@ModelAttribute(value = "updateManicuristRequest") UpdateManicuristRequest request,
                                   Model model) {
        ManicuristRequest manicuristRequest = ManicuristRequest.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .hireDate(request.getHireDate())
                .build();
        manicuristService.updateManicurist(manicuristRequest);

        model.addAttribute("manicurists", manicuristService.allManicurists());
        return "allManicuristsPage";
    }

    @GetMapping("/manicurist/goToCreateManicuristPage")
    public String goToCreateManicuristPage() {
        return "manicuristCreatePage";
    }


    @PostMapping("/manicurist/createNewManicurist")
    public String createManicurist(@ModelAttribute(value = "createManicuristRequest")
                                   CreateManicuristRequest request,
                                   Model model) {
        ManicuristRequest manicuristRequest = ManicuristRequest.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .hireDate(request.getHireDate())
                .build();
        manicuristService.createManicurist(manicuristRequest);

        model.addAttribute("manicurists", manicuristService.allManicurists());
        return "allManicuristsPage";
    }


}
