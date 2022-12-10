package ro.musiclover.manicureappointments.service.interfaces;

import ro.musiclover.manicureappointments.model.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);

}
