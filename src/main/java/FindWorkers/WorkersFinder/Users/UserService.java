package FindWorkers.WorkersFinder.Users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RandomCodeGenerator randomCodeGenerator;

    public User createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
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
    public User requestUpdatePassword(String userName){
        User user = userRepository.findByUsername(userName);
        String code=randomCodeGenerator.generate(5);
        user.setUpdatePasswordCode(code);
        return userRepository.save(user);
    }
    public boolean checkUpdatePasswordCode(String userName,String Code){
        User user = userRepository.findByUsername(userName);
        System.out.println(Code);
        System.out.println(user.getUpdatePasswordCode());
        if(user.getUpdatePasswordCode()==null){
            return false;
        }
        return Code.equals(user.getUpdatePasswordCode());

    }


}
