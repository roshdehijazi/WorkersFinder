package FindWorkers.WorkersFinder;
import FindWorkers.WorkersFinder.issues.Issue;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offers")
public class offerController {
    @Autowired
    private offerService offerService;

    @PostMapping
    public ResponseEntity<?>createOffer(@Valid @RequestBody offer offer){


        try{
            offer createdOffer=offerService.createOffer(offer);
            return ResponseEntity.ok(createdOffer);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping
    public ResponseEntity<List<offer>>getAllOffers(){
        List<offer>offers=offerService.getAllOffersNewerFirst();
        return ResponseEntity.ok(offers);
    }
    @GetMapping("/forIssue")
    public ResponseEntity<List<offer>>getAllOffersForIssue(@RequestBody Map<String, String> request){
        String issueId = request.get("issueId");
        List<offer>offers=offerService.getAllOffersByIssueId(issueId);
        return ResponseEntity.ok(offers);
    }
    @GetMapping("/forWorker")
    public ResponseEntity<List<offer>>getAllOffersForWorker(@RequestBody Map<String, String> request){
        String workerId = request.get("workerId");
        List<offer>offers=offerService.getAllOffersByWorkerId(workerId);
        return ResponseEntity.ok(offers);
    }
}
