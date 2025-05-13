package FindWorkers.WorkersFinder.issues;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "Issue")
public class Issue {
    @Id
    private String id;

    @Field("picture")
    private byte[] imageData;

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
    @Field("startDate")
    private Date startDate;

    public Issue() {
    }

    public Issue(byte[] imageData, String title, String description, String customerId,  Category category, Date startDate) {
        this.imageData = imageData;
        this.title = title;
        this.description = description;
        this.customerId = customerId;
        this.category = category;
        this.startDate = startDate;
        this.isFinished = false;
        this.countViewrs=0;
        this.isAccepted=false;
        this.inProgess=false;
    }


    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isInProgess() {
        return inProgess;
    }

    public void setInProgess(boolean inProgess) {
        this.inProgess = inProgess;
    }

    public int getCountViewrs() {
        return countViewrs;
    }

    public void setCountViewrs(int countViewrs) {
        this.countViewrs = countViewrs;
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWorkerId() {
        return workerId;
    }
    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isFinished() {
        return isFinished;
    }
    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
