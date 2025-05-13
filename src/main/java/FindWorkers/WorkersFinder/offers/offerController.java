package FindWorkers.WorkersFinder.offers;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/offers")
public class offerController {
    @Autowired
    private FindWorkers.WorkersFinder.offers.offerService offerService;

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
    public ResponseEntity<List<offer>>getAllOffersForIssue(@RequestParam String issueId){
        List<offer>offers=offerService.getAllOffersByIssueId(issueId);
        return ResponseEntity.ok(offers);
    }
    @GetMapping("/forWorker")
    public ResponseEntity<List<offer>>getAllOffersForWorker(@RequestParam String workerId){
        List<offer>offers=offerService.getAllOffersByWorkerId(workerId);
        return ResponseEntity.ok(offers);
    }
    @PutMapping("/{offerId}/isAccepted")
    public ResponseEntity<?>markAsAccepted(@PathVariable String offerId){
        offer offer=offerService.markAsAccepted(offerId);
        return ResponseEntity.ok(offer);
    }
    @PutMapping("/{offerId}/isFinished")
    public ResponseEntity<?>markAsFinished(@PathVariable String offerId){
        offer offer=offerService.markAsFinished(offerId);
        return ResponseEntity.ok(offer);
    }
    @PutMapping("/{offerId}/discount")
    public ResponseEntity<offer>requestDiscount(@PathVariable String offerId){
        offer offer=offerService.requestDiscount(offerId);
        return ResponseEntity.ok(offer);
    }
    @DeleteMapping("/{offerId}")
    public ResponseEntity<offer>deleteOfferById(@PathVariable String offerId){
        offerService.deleteOffer(offerId);
        return ResponseEntity.ok().build();
    }


}
