package FindWorkers.WorkersFinder;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

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
    @Field("finished")
    private boolean finished;

    public offer(String description, String workerId, double price, String customerId, String issueId, Date createdAt) {
        this.description = description;
        this.workerId = workerId;
        this.price = price;
        this.customerId = customerId;
        this.issueId = issueId;
        this.createdAt = createdAt;
        this.finished=false;
        this.isAccepted=false;
    }

    public offer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
