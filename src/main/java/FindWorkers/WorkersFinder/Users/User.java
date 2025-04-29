package FindWorkers.WorkersFinder.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User {

    @Id
    @NotBlank(message = "username is required")
    @Field("username")
    @Size(max = 50, message = "Username must be ≤50 characters")
    private String username;

    @ValidEmail
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Field("email")
    private String email;

    @NotBlank(message = "password is required")
    @Field("password")
    private String password;

    @Field("role")
    private String role;
    @Field("category")
    private String category;

    // Constructors
    public User() {}

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

// Getters and Setters




    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
