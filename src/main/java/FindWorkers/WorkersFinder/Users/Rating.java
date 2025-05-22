package FindWorkers.WorkersFinder.Users;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
@Data
@Document(collection = "Rating")


public class Rating {
    @Id
    private String id;
    @Field("description")
    private String description;
    @NotBlank
    @Field("workerId")
    private String workerId;
    @NotBlank
    @Field("Rating")
    private int Rating;
}
