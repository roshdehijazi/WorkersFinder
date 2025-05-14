package FindWorkers.WorkersFinder.issues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/issues")
@CrossOrigin(origins = "http://localhost:3000")
public class IssueController {

    @Autowired
    private IssueService issueService;

//    @PostMapping()
//    public ResponseEntity<?> submitIssue(
//            @RequestParam("title") String title,
//            @RequestParam("description") String description,
//            @RequestParam("category") String category,
//            @RequestParam("customerId") String customerId,
//            @RequestParam("startDate") String startDateStr) {
//
//
//
//        Date startDate;
//        try {
//            startDate = Date.from(Instant.parse(startDateStr));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Invalid date format: " + startDateStr);
//        }
//
//        Issue issue = new Issue(title, description, Category.valueOf(category.toUpperCase()), customerId, startDate);
//        Issue savedIssue = issueService.createIssue(issue);
//
//        return ResponseEntity.ok(savedIssue);
//    }         roshde old post

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitIssueWithImages(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("customerId") String customerId,
            @RequestParam("startDate") String startDateStr,
            @RequestParam(value = "files", required = false) List<MultipartFile> files) {

        Date startDate;
        try {
            startDate = Date.from(Instant.parse(startDateStr));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format: " + startDateStr);
        }

        List<String> base64Images = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                try {
                    byte[] bytes = file.getBytes();
                    String base64 = Base64.getEncoder().encodeToString(bytes);
                    String mimeType = file.getContentType();
                    base64Images.add("data:" + mimeType + ";base64," + base64);
                } catch (IOException e) {
                    return ResponseEntity.internalServerError().body("Image upload failed: " + e.getMessage());
                }
            }
        }

        Issue issue = new Issue(title, description, Category.valueOf(category.toUpperCase()), customerId, startDate);
        issue.setImages(base64Images);

        Issue savedIssue = issueService.createIssue(issue);
        return ResponseEntity.ok(savedIssue);
    }




    @GetMapping("/newer")
    public ResponseEntity<List<Issue>> getAllIssues() {
        List<Issue> issues = issueService.getAllIssues();
        return ResponseEntity.ok(issues);
    }
    @GetMapping("/older")
    public ResponseEntity<List<Issue>> getAllIssuesOlder() {
        List<Issue> issues = issueService.getAllIssuesOlderToNewer();
        return ResponseEntity.ok(issues);
    }
    @GetMapping("/category/newer")
    public ResponseEntity<List<Issue>> getAllIssuesByCategoryNewer(@RequestParam String category) {
        List<Issue> issues = issueService.getAllByCategory(Category.valueOf(category.toUpperCase()));
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/category/older")
    public ResponseEntity<List<Issue>> getAllIssuesByCategoryOlder(@RequestParam String category) {
        List<Issue> issues = issueService.getAllByCategoryOlder(Category.valueOf(category.toUpperCase()));
        return ResponseEntity.ok(issues);
    }
    @GetMapping("customer/{customerId}")
    public ResponseEntity<List<Issue>> getAllIssuesForCustomer(@PathVariable String customerId) {
        List<Issue> issues = issueService.getAllIssuesFroCustomer(customerId);
        return ResponseEntity.ok(issues);
    }
    @GetMapping("/{issueId}")
    public ResponseEntity<Issue>findIssueById(@PathVariable String issueId){
        Issue issue=issueService.findById(issueId);
        return ResponseEntity.ok(issue);
    }


    @PutMapping("/{issueId}/finish")
    public ResponseEntity<Issue> markIssueAsFinished(@PathVariable String issueId) {
        Issue finishedIssue = issueService.markAsFinished(issueId);
        return ResponseEntity.ok(finishedIssue);
    }
    @PutMapping("/{issueId}/addCount")
    public ResponseEntity<Issue> addCount(@PathVariable String issueId) {
        Issue issue=issueService.addViewer(issueId);
        return ResponseEntity.ok(issue);
    }
    @PutMapping("/{issueId}/addWorker")
    public ResponseEntity<Issue> addWorker(@PathVariable String issueId ,
                                           @RequestBody Map<String, String> request) {
        String workerId = request.get("workerId");
        Issue issue =issueService.addWorker(issueId,workerId);
        return ResponseEntity.ok(issue);
    }



//    @PutMapping("/{issueId}/updateImage")
//    public ResponseEntity<Issue> updateImage(@PathVariable String issueId ,
//                                             @RequestParam(value = "image", required = false) MultipartFile image) {
//        String imagePath = null;
//        if (image != null && !image.isEmpty()) {
//            imagePath = image.getOriginalFilename();
//        }
//        Issue issue =issueService.updateImage(issueId,imagePath);
//        return ResponseEntity.ok(issue);
//    } put mapping roshde one

    @PutMapping("/{issueId}/updateTitle")
    public ResponseEntity<Issue> updateTitle(@PathVariable String issueId ,
                                             @RequestBody Map<String, String> request) {
        String title = request.get("title");
        Issue issue =issueService.updateTitle(issueId,title);
        return ResponseEntity.ok(issue);
    }
    @PutMapping("/{issueId}/updateDescription")
    public ResponseEntity<Issue> updateDescription(@PathVariable String issueId ,
                                             @RequestBody Map<String, String> request) {
        String description = request.get("description");
        Issue issue =issueService.updateDescription(issueId,description);
        return ResponseEntity.ok(issue);
    }
    @PutMapping("/{issueId}/updateCategory")
    public ResponseEntity<Issue> updateCategory(@PathVariable String issueId ,
                                                @RequestBody Map<String, String> request) {
        String category=request.get("category");
        Issue issue =issueService.updateCategory(issueId,Category.valueOf(category.toUpperCase()));
        return ResponseEntity.ok(issue);
    }
    @DeleteMapping("/{issueId}")
    public ResponseEntity<Issue> deleteIssue(@PathVariable String issueId) {
        issueService.deleteIssue(issueId);
        return ResponseEntity.ok().build();
    }



}
