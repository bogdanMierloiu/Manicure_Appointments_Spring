package ro.musiclover.manicureappointments.controller.rest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ro.musiclover.manicureappointments.entity.Manicurist;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristRequest;
import ro.musiclover.manicureappointments.model.manicurist.ManicuristResponse;
import ro.musiclover.manicureappointments.repository.ManicuristRepository;
import ro.musiclover.manicureappointments.service.ManicuristService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManicuristControllerTest {

    @Test
    void createManicurist() {
        ManicuristService manicuristService = Mockito.mock(ManicuristService.class);
        ManicuristController manicuristController = new ManicuristController(manicuristService);
        ManicuristRequest manicurist = new ManicuristRequest();
        manicurist.setFirstName("Test First Name");
        manicurist.setLastName("Test Last Name");
        manicurist.setHireDate(LocalDate.of(2023, 1, 9));
        manicurist.setPhoneNumber("0722111333");
        manicuristController.createManicurist(manicurist);
    }

    @Test
    void showAllManicurists() {
        ManicuristService manicuristService = Mockito.mock(ManicuristService.class);
        ManicuristController manicuristController = new ManicuristController(manicuristService);
        manicuristController.showAllManicurists();
    }

    @Test
    void findManicuristById() {
    }

    @Test
    void updateManicurist() {
    }

    @Test
    void deleteManicurist() {
    }
}