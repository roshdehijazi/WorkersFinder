package FindWorkers.WorkersFinder;

import FindWorkers.WorkersFinder.Users.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepository IssueRepostiry;

    public  Issue createIssue(Issue Issue){
        return IssueRepostiry.save(Issue);
    }

    public Issue isFinished(String IsuueId){
        Issue Issue =IssueRepostiry.findById(IsuueId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Issue.setFinished(true);
        return IssueRepostiry.save(Issue);
    }
    public List<Issue> getAllIssues() {
        return IssueRepostiry.findAll();
    }
}
