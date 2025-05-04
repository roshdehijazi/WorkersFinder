package FindWorkers.WorkersFinder;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssueRepository extends MongoRepository<Issue, String> {
        List<Issue> findAllByCustomerId(String customerId);
        List<Issue> findAllByWorkerId(String workerId);


}
