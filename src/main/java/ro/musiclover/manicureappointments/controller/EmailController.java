package ro.musiclover.manicureappointments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.musiclover.manicureappointments.model.EmailDetails;
import ro.musiclover.manicureappointments.service.EmailService;


@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PutMapping("/sendMail")
    public void sendMail(@RequestBody EmailDetails details) {
       emailService.sendSimpleMail(details);
    }

    @PutMapping("/sendMailWithAttachment")
    public void sendMailWithAttachment(@RequestBody EmailDetails details) {
       emailService.sendMailWithAttachment(details);
    }

}
