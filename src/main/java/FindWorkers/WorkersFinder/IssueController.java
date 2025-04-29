package FindWorkers.WorkersFinder;


import FindWorkers.WorkersFinder.Users.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/issues")
public class IssueController {
    @Autowired
    private IssueService IssueService;

    @PostMapping
    public ResponseEntity<?> submitIssue(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        // Save image path or handle storing image to file system / cloud
        String imagePath = null;
        if (image != null && !image.isEmpty()) {
            imagePath = image.getOriginalFilename();
            // For now, we just log or store name
        }

        Issue issue = new Issue(imagePath, title, description, Category.valueOf(category.toUpperCase()));
        IssueService.createIssue(issue);

        return ResponseEntity.ok("Issue submitted successfully"  + issue);
    }
    @PutMapping("/{issueId}/finish")
    public ResponseEntity<?> createIssue( @PathVariable String issueId){
        Issue finishedIssue=IssueService.isFinished(issueId);
        return ResponseEntity.ok(finishedIssue);
    }



}
