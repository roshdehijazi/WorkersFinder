package FindWorkers.WorkersFinder.EmailSender;

import FindWorkers.WorkersFinder.Users.RandomCodeGenerator;
import FindWorkers.WorkersFinder.Users.User;
import FindWorkers.WorkersFinder.Users.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/Email")
public class EmailController {

    private final EmailService emailService;
    private final RandomCodeGenerator randomCodeGenerator;
    private final UserRepository userRepository;

    @Autowired
    public EmailController(
            EmailService emailService,
            RandomCodeGenerator randomCodeGenerator,
            UserRepository userRepository
    ) {
        this.emailService = emailService;
        this.randomCodeGenerator = randomCodeGenerator;
        this.userRepository = userRepository;
    }

    @PostMapping("/send-reset")
    public ResponseEntity<?> sendResetEmail(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body("Email not found");
        }

        String resetCode = randomCodeGenerator.generate(6);
        String resetLink = "http://localhost:3000/reset-password/" + user.getUsername() + "?code=" + resetCode;

        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getUsername());
        model.put("resetCode", resetCode);
        model.put("resetLink", resetLink);

        try {
            // save code + expiry
            user.setUpdatePasswordCode(resetCode);
            user.setUpdatePasswordCodeExpiry(new Date(System.currentTimeMillis() + 15 * 60 * 1000));
            userRepository.save(user);

            emailService.sendHtmlEmail(email, "Reset Your Password", "passwordReset", model);
            return ResponseEntity.ok("Reset email sent");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error sending email: " + e.getMessage());
        }
    }

}
