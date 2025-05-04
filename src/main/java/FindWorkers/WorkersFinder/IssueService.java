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
    public List<Issue> getAllIssuesFroCustomer(String customerId) {
        return issueRepository.findAllByCustomerId(customerId);
    }
    public void deleteIssue(String issueId){
        if(!issueRepository.existsById(issueId))
            throw new RuntimeException("User not found with id: " + issueId);
        issueRepository.deleteById(issueId);
    }
    public Issue addViewer (String issueId){
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        int count= issue.getCountViewrs();
        count++;
        issue.setCountViewrs(count);
        return issueRepository.save(issue);

    }
    public Issue addWorker (String issueId,String workerId){
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setWorkerId(workerId);
        return issueRepository.save(issue);
    }
    public Issue updateImage (String issueId ,String image){
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setPicture(image);
        return issueRepository.save(issue);
    }
    public Issue updateTitle (String issueId ,String title){
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setTitle(title);
        return issueRepository.save(issue);
    }
    public Issue updateCategory (String issueId ,Category category){
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setCategory(category);
        return issueRepository.save(issue);
    }
    public Issue updateDescription (String issueId ,String description){
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        issue.setDescription(description);
        return issueRepository.save(issue);
    }

}
