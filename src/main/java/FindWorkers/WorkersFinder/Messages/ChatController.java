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
    public ResponseEntity<?> createRoom(@RequestParam ChatRoom chatRoom, @RequestParam Message message) {
        if(!chatService.ifChatRoomExistsByName(chatRoom.getName())) {
           chatService.createChatRoom(chatRoom);
        }
        ChatRoom createdChat=chatService.findByName(chatRoom.getName());
        message.setChatRoomId(createdChat.getId());
        chatService.sendMessage(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/rooms/{userId}")
    public List<ChatRoom> getUserRooms(@PathVariable String userId) {
        return chatService.getUserChatRooms(userId);
    }

    @PostMapping("/messages")
    public Message sendMessage(@RequestBody Message message) {
        return chatService.sendMessage(message);
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