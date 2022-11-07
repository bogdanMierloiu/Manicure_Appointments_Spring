package ro.musiclover.manicureappointments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.service.implementation.ManicuristService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("manicurist")
@RequiredArgsConstructor
public class ManicuristController {
    private final ManicuristService manicuristService;

    @PostMapping("/create")
    public void createManicurist(@RequestBody Manicurist manicurist) {
        manicuristService.createManicurist(manicurist);
    }

    @GetMapping("/list")
    public List<Manicurist> showAllManicurists() {
        return manicuristService.allManicurists();
    }

    @GetMapping("/find/{id}")
    public Optional<Manicurist> findManicuristById(@PathVariable Integer id) {
        return manicuristService.findManicuristById(id);
    }

    @PutMapping("/update/{id}")
    public void updateManicurist(@PathVariable Integer id, @RequestBody Manicurist manicurist) {
        manicuristService.updateManicurist(id, manicurist);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteManicurist(@PathVariable Integer id) {
        manicuristService.deleteManicurist(id);
    }


}
