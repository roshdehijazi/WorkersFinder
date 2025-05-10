package FindWorkers.WorkersFinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class offerService {
    @Autowired
    private offerRepository offerRepository;

    public offer createOffer(offer offer){
        return offerRepository.save(offer);

    }
}
