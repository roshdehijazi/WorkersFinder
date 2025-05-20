package FindWorkers.WorkersFinder.EmailSender;

import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


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
    @GetMapping("/send-email")
    public String sendEmail() throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("name", "John Doe");
        model.put("subject", "Welcome to Our Service");
        model.put("header", "Account Registration");
        model.put("message", "Thank you for registering with our service.");
        model.put("link", "https://example.com/activate");
        model.put("linkText", "Activate Your Account");

        emailService.sendHtmlEmail(
                "roshde159@gmail.com",
                "Welcome to Our Service",
                "email-template",
                model
        );

        return "Email sent successfully!";
    }
}
