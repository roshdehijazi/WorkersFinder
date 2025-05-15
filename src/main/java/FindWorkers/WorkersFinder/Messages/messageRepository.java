package FindWorkers.WorkersFinder.Messages;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface messageRepository extends MongoRepository<Message, String> {
    List<Message> findByChatRoomIdOrderByTimestamp(String chatRoomId);


}
