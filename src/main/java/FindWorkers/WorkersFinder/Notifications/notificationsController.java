package FindWorkers.WorkersFinder.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Notifications")
public class notificationsController {
    @Autowired
    NotificationsService notificationsService;
    @PostMapping
    public Notifications createNotification(@RequestBody Notifications notification){
        return notificationsService.createNotification(notification);
    }
    @GetMapping("{userId}")
    public List<Notifications> getAllNotifications (@PathVariable String userId){
        return notificationsService.getAllNotifications(userId);
    }
    @GetMapping("getUnRead/{userId}")
    public List<Notifications> getUnReadNotifications (@PathVariable String userId){
        return notificationsService.getAllUnreadNotifications(userId);
    }
    @GetMapping("getCountUnRead/{userId}")
    public Long getCountUnReadNotifications (@PathVariable String userId){
        return notificationsService.getCountUnReadNotifications(userId);
    }
    @PutMapping("markAsRead/{notificationId}")
    public Notifications markAsRead(@PathVariable String notificationId){
        return notificationsService.markAsRead(notificationId);
    }

}
