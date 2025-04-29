package FindWorkers.WorkersFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    public Issue markAsFinished(String issueId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setFinished(true);
        return issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }
}
