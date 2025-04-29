package FindWorkers.WorkersFinder;
import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Issue")
public class Issue {
    @Id
    private String id;
    @Field("picture")
    private String picture;
    @NotBlank(message = "title is required")
    @Field("Title")
    private String Title;
    @Field("description")
    private String decscription;
    @Field("coustomerId")
    private String customerId;
    @Field("workerId")
    private String workerId;

    @Field("category")
    private Category category;
    @Field("IsFinished")
    private boolean IsFinished;
    @Field("StartDate")
    private Date startDate;


    public Issue() {
    }





    public Issue(String imagePath, String title, String description, Category category, String customerId,Date startDate) {
        this.picture = imagePath;
        Title = title;
        this.decscription = description;
        this.customerId = customerId;
        this.category = category;
        this.startDate = startDate;
        this.IsFinished=false;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isFinished() {
        return IsFinished;
    }

    public void setFinished(boolean finished) {
        IsFinished = finished;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDecscription() {
        return decscription;
    }

    public void setDecscription(String decscription) {
        this.decscription = decscription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
