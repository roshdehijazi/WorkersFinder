package FindWorkers.WorkersFinder.Users;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public boolean authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return passwordEncoder.matches(rawPassword, user.getPassword());
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





