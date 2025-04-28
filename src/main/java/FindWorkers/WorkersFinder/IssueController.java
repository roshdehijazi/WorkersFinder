package FindWorkers.WorkersFinder;


import FindWorkers.WorkersFinder.Users.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {
    @Autowired
    private IssueService IssueService;

    @PostMapping
    public ResponseEntity<?> createIssue(@Valid @RequestBody Issue Issue) {
        try {
            Issue createdIssue = IssueService.createIssue(Issue);
            return ResponseEntity.ok(createdIssue);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{issueId}/finish")
    public ResponseEntity<?> createIssue( @PathVariable String issueId){
        Issue finishedIssue=IssueService.isFinished(issueId);
        return ResponseEntity.ok(finishedIssue);
    }
    @GetMapping
    public ResponseEntity<List<Issue>> getAllUsers() {
        List<Issue> issues = IssueService.getAllIssues();
        return ResponseEntity.ok(issues);
    }



}
