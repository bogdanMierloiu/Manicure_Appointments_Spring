package ro.musiclover.manicureappointments.controller.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Appointment {

    @GetMapping("/appointment")
    public String goToAppointmentsPage(){
        return "appointmentPage";
    }
}
