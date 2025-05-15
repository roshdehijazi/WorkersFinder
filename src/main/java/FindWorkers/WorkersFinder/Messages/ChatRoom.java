package FindWorkers.WorkersFinder.Messages;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Data
@Document(collection = "chat_rooms")
public class ChatRoom {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private List<String> participantIds;
}