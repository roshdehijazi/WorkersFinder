package FindWorkers.WorkersFinder;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface offerRepository  extends MongoRepository<offer, String> {

    List<offer> findAllByOrderByCreatedAtDesc();
    List<offer>findByIssueIdOrderByCreatedAtDesc(String issueId);
    List<offer>findByWorkerIdOrderByCreatedAtDesc(String workerId);
}
