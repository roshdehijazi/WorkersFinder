package FindWorkers.WorkersFinder.Users;


import FindWorkers.WorkersFinder.EmailSender.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RandomCodeGenerator randomCodeGenerator;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private EmailService emailService;

    public User createUser(User user) {
        String username=user.getUsername();
        boolean hasSymbol = username.matches(".*[^a-zA-Z0-9].*");
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if(hasSymbol)
            throw new RuntimeException("wrong username");

        User savedUser = userRepository.save(user);
        Map<String, Object> model = new HashMap<>();
        model.put("name", savedUser.getUsername());

        try {
            System.out.println("Sending welcome email to: " + savedUser.getEmail());

            emailService.sendHtmlEmail(
                    savedUser.getEmail(),
                    "Welcome to FindWorkers!",
                    "welcomeTemplate",
                    model
            );
            System.out.println(" Welcome email sent!");
        } catch (Exception e) {
            System.err.println("Failed to send welcome email: " + e.getMessage());
        }


        return savedUser;
    }


    public void deleteUser(String userId) {

        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        userRepository.deleteById(userId);
    }


    public boolean authenticate(String userId, String rawPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
    public ResponseEntity<Object> updatePassword(String userId, String newPassword,String oldPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if ( authenticate(user.getId(), oldPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(401).body(null);
        }
    }
    public User ForgetPassword(String userId, String newPassword){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatePasswordCode(null);
        userRepository.save(user);
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User findUserById(String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user;

    }

    public User updateUsername(String userId,String newUsername){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(newUsername);
        return userRepository.save(user);
    }
    public User requestUpdatePassword(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String code = randomCodeGenerator.generate(6);
        Date expiryTime = new Date(System.currentTimeMillis() + 15 * 60 * 1000);

        user.setUpdatePasswordCode(code);
        user.setUpdatePasswordCodeExpiry(expiryTime);
        userRepository.save(user); // persist changes to DB

        // Build reset link for frontend
        String resetLink = "http://localhost:3000/reset-password/" + user.getUsername() + "?code=" + code;

        // Build email model for Thymeleaf template
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getUsername());
        model.put("resetCode", code);
        model.put("resetLink", resetLink);

        try {
            emailService.sendHtmlEmail(
                    user.getEmail(),
                    "Reset Your Password",
                    "passwordReset",
                    model
            );
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to send password reset email", e);
        }

        return user;
    }




    public boolean checkUpdatePasswordCode(String userName, String code){
        User user = userRepository.findByUsername(userName);
        if (user == null || user.getUpdatePasswordCode() == null)
            return false;

        Date now = new Date();
        return code.equals(user.getUpdatePasswordCode()) &&
                user.getUpdatePasswordCodeExpiry() != null &&
                now.before(user.getUpdatePasswordCodeExpiry());
    }

    public User addRating(Rating rating){
        User user=userRepository.findById(rating.getWorkerId())
                .orElseThrow(() -> new RuntimeException("user not found"));
        if(rating.getRating()>5)
            throw new RuntimeException("the rating is wrong");
        ratingRepository.save(rating);
        long countRating=ratingRepository.countByWorkerId(rating.getWorkerId());
        long sumRating =ratingRepository.sumRatingByWorkerId(rating.getWorkerId());
        System.out.println(countRating);
        System.out.println(sumRating);
        double Rating = (double) sumRating /countRating;
        user.setRating(Rating);
        userRepository.save(user);
        return user;

    }
    public List<Rating>getAllRating(){
        return ratingRepository.findAll();
    }
    public List<Rating>getAllForWorker(String workerId){
        return ratingRepository.findByWorkerId(workerId);
    }



}
