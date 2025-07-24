package FindWorkers.WorkersFinder.offers;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
@Data
@Document(collection = "offers")
public class offer {
    @Id
    private String id;
    @Field("description")
    @NotBlank(message = "description is required")
    private String description;
    @Field("workerId")
    @NotBlank(message = "workerId is required")
    private String workerId;
    @Field("price")
    private double price;
    @Field("customerId")
    @NotBlank(message = "customerID is required")
    private String customerId;
    @Field("issueId")
    @NotBlank(message = "issuesId is required")
    private String issueId;
    @Field("createdAt")
    private Date createdAt;
    @Field("isAccepted")
    private boolean isAccepted;
    @Field("isRated")
    private boolean isRated;
    @Field("finished")
    private boolean finished;
    @Field("discounted")
    private boolean discounted;

}
