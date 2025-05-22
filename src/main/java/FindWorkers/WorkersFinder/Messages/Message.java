package FindWorkers.WorkersFinder.Messages;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.Date;
import java.util.Map;

@Data
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String chatRoomId;
    private String senderId;
    private String content;
    private boolean isRead;
    private Date timestamp = new Date();
    private Map<String, Object> meta;

}

