package FindWorkers.WorkersFinder.Users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import FindWorkers.WorkersFinder.Users.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("userName/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable String userId){
        User user=userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(
            @PathVariable String userId,
            @RequestBody Map<String, String> request)

    {
        String oldPassword=request.get("oldPassword");
        String newPassword = request.get("newPassword");
        return userService.updatePassword(userId, newPassword,oldPassword);

    }
    @PutMapping("/{userId}/updateUsername")
    public User updateUsername(
            @PathVariable String userId,
            @RequestBody Map<String, String> request)

    {
        String username= request.get("username");
        return userService.updateUsername(userId,username);

    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/requestUpdatePassword/{userName}")
    public ResponseEntity<?> verifyResetCode(
            @PathVariable String userName,
            @RequestParam String code) {

        System.out.println("🔍 Checking reset for user: " + userName + ", code: " + code);

        User user = userRepository.findByUsername(userName);
        if (user == null) {
            System.out.println("❌ User not found in DB");
            return ResponseEntity.status(404).body("User not found");
        }

        System.out.println("✅ User found: " + user.getUsername());
        System.out.println("💾 Stored reset code: " + user.getUpdatePasswordCode());
        System.out.println("⏳ Expiry time: " + user.getUpdatePasswordCodeExpiry());

        if (user.getUpdatePasswordCode() == null || !user.getUpdatePasswordCode().equals(code)) {
            System.out.println("❌ Provided code does not match or is missing");
            return ResponseEntity.status(400).body("Invalid reset code");
        }

        if (user.getUpdatePasswordCodeExpiry() == null || user.getUpdatePasswordCodeExpiry().before(new Date())) {
            System.out.println("❌ Code has expired");
            return ResponseEntity.status(410).body("Reset code has expired");
        }

        System.out.println("✅ Code is valid and not expired");
        return ResponseEntity.ok("Code is valid");
    }


    @GetMapping("/checkUpdatePasswordCode/{userName}")
    public boolean checkUpdatePasswordCode(
            @PathVariable String userName,
            @RequestParam("code") String code) {
        return userService.checkUpdatePasswordCode(userName, code);
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePasswordAfterReset(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String code = request.get("code");
        String newPassword = request.get("newPassword");

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        if (user.getUpdatePasswordCode() == null || !user.getUpdatePasswordCode().equals(code)) {
            return ResponseEntity.status(400).body("Invalid code");
        }

        if (user.getUpdatePasswordCodeExpiry() == null || user.getUpdatePasswordCodeExpiry().before(new Date())) {
            return ResponseEntity.status(410).body("Code expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatePasswordCode(null); // clear reset code
        user.setUpdatePasswordCodeExpiry(null);
        userRepository.save(user);

        return ResponseEntity.ok("Password updated successfully");
    }



    @PutMapping("/ForgetPassword/{userId}")
    public ResponseEntity<?>ForgetPassword(@PathVariable String userId,@RequestBody Map<String, String> request){
        String newPassword=request.get("newPassword");
        User user=userService.ForgetPassword(userId,newPassword);
        return ResponseEntity.ok(user);
    }
    @PutMapping("/addRating")
    public ResponseEntity<User>addRating (@RequestBody Rating rating){
        User user=userService.addRating(rating);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/getAllRating")
    public ResponseEntity<List<Rating>> getAllRating(){
        List<Rating> rating=userService.getAllRating();
        return ResponseEntity.ok(rating);
    }
    @GetMapping("/getAllRating/{workerId}")
    public ResponseEntity<List<Rating>> getAllRatingForWorker(@PathVariable String workerId){
        List<Rating> rating=userService.getAllForWorker(workerId);
        return ResponseEntity.ok(rating);
    }



}
