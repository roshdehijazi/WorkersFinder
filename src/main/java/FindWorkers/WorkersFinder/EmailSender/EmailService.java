package FindWorkers.WorkersFinder.EmailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private final JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

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
    public void sendHtmlEmail(String to, String subject, String templateName,
                              Map<String, Object> templateModel) throws MessagingException {

        Context context = new Context();
        context.setVariables(templateModel);
        String htmlContent = templateEngine.process(templateName, context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("WorkersFinder@Service.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        // ✅ Embed logo from resources (adjust path if needed)
        File logo = new File("src/main/resources/static/logo.png");
        if (logo.exists()) {
            helper.addInline("logoImage", logo);
        } else {
            System.err.println("⚠ Logo image not found at: " + logo.getAbsolutePath());
        }

        mailSender.send(message);
    }




}
