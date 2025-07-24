package FindWorkers.WorkersFinder.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotBlank(message = "username is required")
    @Field("username")
    @Size(max = 50, message = "Username must be ≤50 characters")
    private String username;
    @ValidEmail
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Indexed(unique = true)
    @Field("email")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 6, message = "password must be min 6 char")
    @Field("password")
    private String password;
    @Field("role")
    private Role role;
    @Field("category")
    private String category;
    @Field("Rating")
    private double Rating;
    @Field("updatePasswordCode")
    private String updatePasswordCode;
    @Field("updatePasswordCodeExpiry")
    private Date updatePasswordCodeExpiry;

}
