package FindWorkers.WorkersFinder.EmailSender;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/Email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendTextEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text) {
        try {
            emailService.sendSimpleEmail(to, subject, text);
            return ResponseEntity.ok("Plain text email sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to send email");
        }
    }
}
