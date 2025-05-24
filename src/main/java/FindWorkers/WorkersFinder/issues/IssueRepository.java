package FindWorkers.WorkersFinder.issues;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssueRepository extends MongoRepository<Issue, String> {
        List<Issue> findByCustomerIdOrderByStartDateDesc(String customerId);
        List<Issue> findByIsFinishedTrue();
        List<Issue> findByIsFinishedFalse();
        List<Issue> findByIsAcceptedTrueAndIsFinishedFalse();
        List<Issue> findByIsAcceptedFalse();
        List<Issue> findByCategoryAndIsAcceptedFalseOrderByStartDateDesc(Category category);
        List<Issue> findByIsAcceptedFalseOrderByStartDateDesc();
        List<Issue> findByIsAcceptedFalseOrderByStartDateAsc();
        List<Issue> findByCategoryAndIsAcceptedFalseOrderByStartDateAsc(Category category);


}
