package FindWorkers.WorkersFinder.Notifications;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationsRepository extends MongoRepository<Notifications, String> {
    List<Notifications> findByUserId(String userId);
    List<Notifications> findByUserIdAndIsReadFalse(String userId);
    long countByUserIdAndIsReadFalse(String userId);


}
