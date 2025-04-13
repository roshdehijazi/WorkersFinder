package FindWorkers.WorkersFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public boolean authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        // Check if raw password matches the hashed password
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody loginRequest loginRequest) {
        User user = userService.getUserByUsername(loginRequest.getUsername());
        if (user != null && authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
            String token = JwtUtil.generateToken(user.getUsername());
            LoginResponse loginResponse = new LoginResponse(token, user);
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }


}





