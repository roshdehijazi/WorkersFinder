package FindWorkers.WorkersFinder.issues;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssueRepository extends MongoRepository<Issue, String> {

        List<Issue> findAllByOrderByStartDateDesc();
        List<Issue> findByCustomerIdOrderByStartDateDesc(String customerId);
        List<Issue> findByWorkerIdOrderByStartDateDesc(String workerId);
        List<Issue> findByCategoryOrderByStartDateDesc(Category category);
        List<Issue> findAllByOrderByStartDateAsc();
        List<Issue> findByCustomerIdOrderByStartDateAsc(String customerId);
        List<Issue> findByWorkerIdOrderByStartDateAsc(String workerId);
        List<Issue> findByCategoryOrderByStartDateAsc(Category category);

}
