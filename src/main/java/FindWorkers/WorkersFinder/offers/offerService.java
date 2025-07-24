package FindWorkers.WorkersFinder.offers;

import FindWorkers.WorkersFinder.Users.User;
import FindWorkers.WorkersFinder.Users.UserRepository;
import FindWorkers.WorkersFinder.issues.Issue;
import FindWorkers.WorkersFinder.issues.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class offerService {
    @Autowired
    private offerRepository offerRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private UserRepository userRepository;

    public offer createOffer(offer offer){
        return offerRepository.save(offer);

    }
    public List<offer>getAllOffersNewerFirst(){
        return offerRepository.findAllByOrderByCreatedAtDesc();
    }
    public List<offer>getAllOffersByIssueId(String issueId){
        return offerRepository.findByIssueIdOrderByCreatedAtDesc(issueId);
    }
    public List<offer>getAllOffersByWorkerId(String workerId){
        return offerRepository.findByWorkerIdOrderByCreatedAtDesc(workerId);
    }
    public offer markAsAccepted(String offerId){
        offer offer=offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("offer not found"));
        offer.setAccepted(true);
        return offerRepository.save(offer);
    }
    public offer markAsFinished(String offerId){
        offer offer=offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("offer not found"));
        offer.setFinished(true);
        return offerRepository.save(offer);
    }
    public void deleteOffer(String offerId){
        if(!offerRepository.existsById(offerId))
            throw new RuntimeException("offer not found with id: " + offerId);
        offerRepository.deleteById(offerId);
    }
    public offer acceptDiscount(String offerId){
        offer offer=offerRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("offer not found"));
        double newPrice=offer.getPrice();
        newPrice=newPrice-(newPrice*10/100);
        offer.setPrice(newPrice);
        offer.setDiscounted(false);
        return offerRepository.save(offer);
    }
    public offer markAsRated(String offerId) {
        offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));
        offer.setRated(true);
        return offerRepository.save(offer);
    }
    public offer isDiscounted (String offerId){
        offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));
        offer.setDiscounted(true);
        return offerRepository.save(offer);
    }
    public void cancelOffer(String offerId){
        offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));
        String issueId=offer.getIssueId();
        Issue issue=issueRepository.findById(issueId)
                .orElseThrow(()->new IllegalArgumentException("issue not fount"));
        issue.setAccepted(false);
        issueRepository.save(issue);
        offerRepository.deleteById(offerId);
    }
    public String getWorkerUserName(String offerId){
        offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));
        String workerId=offer.getWorkerId();
        User user=userRepository.findById(workerId)
                .orElseThrow(()->new IllegalArgumentException("Worker not found"));
        String userName=user.getUsername();
        return userName;

    }


}
