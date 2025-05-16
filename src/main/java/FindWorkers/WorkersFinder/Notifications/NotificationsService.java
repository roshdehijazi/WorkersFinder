package FindWorkers.WorkersFinder.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService {
    @Autowired
    private NotificationsRepository notificationsRepository;

    public Notifications createNotification(Notifications notification){
        return notificationsRepository.save(notification);
    }
    public List<Notifications>getAllUnreadNotifications(String userId){
        return notificationsRepository.findByUserIdAndIsReadFalse(userId);
    }
    public Long getCountUnReadNotifications(String userId){
        return notificationsRepository.countByUserIdAndIsReadFalse(userId);
    }
    public List<Notifications>getAllNotifications(String userId){
        return notificationsRepository.findByUserId(userId);
    }
    public Notifications markAsRead(String notificationId){
        Notifications notification=notificationsRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        return notificationsRepository.save(notification);
    }
}
