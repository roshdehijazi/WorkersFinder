package FindWorkers.WorkersFinder.Notifications;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;
@Data
@Document(collection = "Notifications")
public class Notifications {
    @Id
    private String id;
    private String userId;
    private String message;
    private boolean isRead;
    

}
