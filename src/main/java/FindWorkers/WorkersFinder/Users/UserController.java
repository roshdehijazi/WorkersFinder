package FindWorkers.WorkersFinder.Users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;



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
    @PutMapping("/requestUpdatePassword/{userName}")
    public ResponseEntity<User>requestUpdatePassword(@PathVariable String userName){
        User user=userService.requestUpdatePassword(userName);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/checkUpdatePasswordCode/{userName}")
    public boolean checkUpdatePasswordCode(@PathVariable String userName,@RequestBody Map<String, String> request){
        String Code=request.get("Code");
        return userService.checkUpdatePasswordCode(userName,Code);

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
