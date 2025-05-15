package FindWorkers.WorkersFinder.Messages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final messageRepository messageRepository;

    public ChatService(ChatRoomRepository chatRoomRepository, messageRepository messageRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
    }

    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getUserChatRooms(String userId) {
        return chatRoomRepository.findByParticipantIdsContaining(userId);
    }

    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getChatHistory(String chatRoomId) {
        return messageRepository.findByChatRoomIdOrderByTimestamp(chatRoomId);
    }
    public Message markAsRead(String messageId){
        Message message=messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("message not found"));
        message.setRead(true);
        return messageRepository.save(message);
    }
    public boolean ifChatExistsByName(String chatName){
        return messageRepository.existsByName(chatName);
    }
}
