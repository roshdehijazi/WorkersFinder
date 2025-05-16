package FindWorkers.WorkersFinder.issues;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;
@Data
@Document(collection = "Issue")
public class Issue {
    @Id
    private String id;
    @NotBlank(message = "Title is required")
    @Field("title")
    private String title;
    @Field("description")
    private String description;
    @Field("customerId")
    private String customerId;
    @Field("workerId")
    private String workerId;
    @Field("category")
    private Category category;
    @Field("isFinished")
    private boolean isFinished;
    @Field("countViewrs")
    private int countViewrs;
    @Field("isAccepted")
    private boolean isAccepted;
    @Field("inProgess")
    private boolean inProgess;
    @Field("images")
    private List<String> images;
    @Field("startDate")
    private Date startDate;
    public Issue(String title, String description, Category category, String customerId, Date startDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.customerId = customerId;
        this.startDate = startDate;
        this.isFinished = false;
        this.isAccepted=false;
        this.inProgess=false;
        this.countViewrs=0;
    }
}
