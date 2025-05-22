package FindWorkers.WorkersFinder.Messages;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ChatRoomRequest {
    // Getters & Setters
    private String name;
    private List<String> participantIds;
    private String senderId;
    private String content;
    private Date timestamp;
    private Map<String, Object> meta;

}
