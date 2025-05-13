package FindWorkers.WorkersFinder.Messages;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    List<ChatRoom> findByParticipantIdsContaining(String userId);
}
