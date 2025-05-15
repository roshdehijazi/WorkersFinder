package FindWorkers.WorkersFinder.Messages;

import java.util.Date;
import java.util.List;

public class ChatRoomRequest {
    private String name;
    private List<String> participantIds;
    private String senderId;
    private String content;
    private Date timestamp;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getParticipantIds() { return participantIds; }
    public void setParticipantIds(List<String> participantIds) { this.participantIds = participantIds; }

    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}
