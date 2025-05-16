package FindWorkers.WorkersFinder.Messages;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<?> createRoomAndSendMessage(@RequestBody ChatRoomRequest request) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(request.getName());
        chatRoom.setParticipantIds(request.getParticipantIds());

        if (!chatService.ifChatRoomExistsByName(chatRoom.getName())) {
            chatService.createChatRoom(chatRoom);
        }

        ChatRoom createdChat = chatService.findByName(chatRoom.getName());

        Message message = new Message();
        message.setChatRoomId(createdChat.getId());
        message.setSenderId(request.getSenderId());
        message.setContent(request.getContent());
        message.setTimestamp(request.getTimestamp());

        chatService.sendMessage(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/rooms/{userId}")
    public List<ChatRoom> getUserRooms(@PathVariable String userId) {
        return chatService.getUserChatRooms(userId);
    }

    @GetMapping("/messages/{chatRoomId}")
    public List<Message> getMessages(@PathVariable String chatRoomId) {

        return chatService.getChatHistory(chatRoomId);
    }
    @PutMapping("/markAsRead/{messageId}")
    public Message markAsRead(@PathVariable String messageId){

        Message readedMessage=chatService.markAsRead(messageId);

        return readedMessage;
    }

}