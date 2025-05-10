package FindWorkers.WorkersFinder;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;

@RestController
@RequestMapping("/offers")
public class offerController {
    @Autowired
    private offerService offerService;

    @PostMapping
    public ResponseEntity<?>createOffer( @RequestBody offer offer){


        try{
            offer createdOffer=offerService.createOffer(offer);
            return ResponseEntity.ok(createdOffer);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
