package FindWorkers.WorkersFinder.Messages;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

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

        // 🛑 Do not send a message if the content is empty/null
        if (request.getContent() != null && !request.getContent().trim().isEmpty()) {
            Message message = new Message();
            message.setChatRoomId(createdChat.getId());
            message.setSenderId(request.getSenderId());
            message.setContent(request.getContent());
            message.setTimestamp(request.getTimestamp());

            if (request.getMeta() != null) {
                message.setMeta(request.getMeta());
            }

            chatService.sendMessage(message);
            return ResponseEntity.ok(message);
        }

        // ✅ Just return the chat room if no message is needed
        return ResponseEntity.ok(createdChat);
    }


    @PostMapping("/messages")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        Message saved = chatService.sendMessage(message);
        return ResponseEntity.ok(saved);
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
    @PutMapping("/messages/{messageId}/meta-status")
    public ResponseEntity<Message> updateMessageMetaStatus(
            @PathVariable String messageId,
            @RequestBody Map<String, String> body
    ) {
        String status = body.get("status"); // expected: "accepted" or "declined"
        Message updated = chatService.updateMessageMetaStatus(messageId, status);
        return ResponseEntity.ok(updated);
    }

}