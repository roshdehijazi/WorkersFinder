package FindWorkers.WorkersFinder.Messages;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/rooms")
    public ChatRoom createRoom(@RequestBody ChatRoom chatRoom) {
        return chatService.createChatRoom(chatRoom);
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
}