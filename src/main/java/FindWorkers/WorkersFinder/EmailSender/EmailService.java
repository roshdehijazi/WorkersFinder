package FindWorkers.WorkersFinder.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
@Service
public class EmailService {

    private final JavaMailSender mailSender;


    public EmailService(JavaMailSender mailSender
                       ) {
        this.mailSender = mailSender;

    }
    public void sendSimpleEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("WorkersFinder@Service.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, false); // false = plain text

        mailSender.send(message);
    }



}
