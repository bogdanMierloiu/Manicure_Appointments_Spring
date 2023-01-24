package ro.musiclover.manicureappointments.controller.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ro.musiclover.manicureappointments.model.appointment.DateBetweenRequest;
import ro.musiclover.manicureappointments.model.appointment.DateBetweenRequestForWeb;
import ro.musiclover.manicureappointments.model.appointment.DateRequest;
import ro.musiclover.manicureappointments.service.AppointmentService;

@Controller
@RequiredArgsConstructor
public class RevenueController {

    private final AppointmentService appointmentService;

    @GetMapping("/revenue")
    public String goToRevenuePage() {
        return "revenuePage";
    }

    @GetMapping("/revenue/forPeriod")
    public String revenueForPeriod(@ModelAttribute DateBetweenRequestForWeb dateRequest, Model model) {
        DateBetweenRequest dateBetweenRequest = DateBetweenRequest.builder()
                .dateFrom(dateRequest.getDateFrom().atStartOfDay())
                .dateTo(dateRequest.getDateTo().plusDays(1).atStartOfDay())
                .build();
        Integer result = appointmentService.revenueForPeriod(dateBetweenRequest);
        model.addAttribute("result", result);
        return "revenuePage";
    }

    @GetMapping("revenue/forDay")
    public String revenueForDay(@ModelAttribute DateRequest dateRequest, Model model) {
        Integer resultForDay = appointmentService.revenueForDay(dateRequest);
        model.addAttribute("resultForDay", resultForDay);
        return "revenuePage";
    }


}
