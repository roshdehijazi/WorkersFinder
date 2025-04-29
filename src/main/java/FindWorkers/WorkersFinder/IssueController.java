package FindWorkers.WorkersFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/issues")
@CrossOrigin(origins = "http://localhost:3000")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitIssue(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("customerId") String customerId,
            @RequestParam("startDate") String startDateStr,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            imagePath = image.getOriginalFilename();
            // Optional: save file to disk here
        }

        Date startDate;
        try {
            startDate = Date.from(Instant.parse(startDateStr));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format: " + startDateStr);
        }

        Issue issue = new Issue(imagePath, title, description, Category.valueOf(category.toUpperCase()), customerId, startDate);
        Issue savedIssue = issueService.createIssue(issue);

        return ResponseEntity.ok(savedIssue);
    }

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        List<Issue> issues = issueService.getAllIssues();
        return ResponseEntity.ok(issues);
    }

    @PutMapping("/{issueId}/finish")
    public ResponseEntity<Issue> markIssueAsFinished(@PathVariable String issueId) {
        Issue finishedIssue = issueService.markAsFinished(issueId);
        return ResponseEntity.ok(finishedIssue);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<List<Issue>> getAllIssuesForCustomer(@PathVariable String customerId) {
        List<Issue> issues = issueService.getAllIssuesFroCustomer(customerId);
        return ResponseEntity.ok(issues);
    }
}
