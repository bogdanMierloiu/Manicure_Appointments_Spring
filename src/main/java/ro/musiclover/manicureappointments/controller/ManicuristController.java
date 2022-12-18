package ro.musiclover.manicureappointments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristRequest;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponse;
import ro.musiclover.manicureappointments.service.ManicuristService;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("manicurist")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Validated
public class ManicuristController {
    private final ManicuristService manicuristService;

    @PostMapping("/create")
    public ManicuristResponse createManicurist(@RequestBody @Valid ManicuristRequest manicuristRequest) {
        return manicuristService.createManicurist(manicuristRequest);
    }

    @GetMapping("/list")
    public List<ManicuristResponse> showAllManicurists() {
        return manicuristService.allManicurists();
    }

    @GetMapping("/find/{id}")
    public ManicuristResponse findManicuristById(@PathVariable Integer id) {
        return manicuristService.findManicuristById(id);
    }

    @PatchMapping("/update")
    public void updateManicurist(@RequestBody @Valid ManicuristRequest manicuristRequest) {
        manicuristService.updateManicurist(manicuristRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteManicurist(@PathVariable Integer id) {
        manicuristService.deleteManicurist(id);
    }


}
