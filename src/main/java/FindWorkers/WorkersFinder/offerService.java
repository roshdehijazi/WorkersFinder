package FindWorkers.WorkersFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class offerService {
    @Autowired
    private offerRepository offerRepository;

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

}
