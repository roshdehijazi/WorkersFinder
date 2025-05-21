package FindWorkers.WorkersFinder.issues;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IssueRepository extends MongoRepository<Issue, String> {

        List<Issue> findAllByOrderByStartDateDesc();
        List<Issue> findByCustomerIdOrderByStartDateDesc(String customerId);
        List<Issue> findByCategoryOrderByStartDateDesc(Category category);
        List<Issue> findAllByOrderByStartDateAsc();
        List<Issue> findByCategoryOrderByStartDateAsc(Category category);

        List<Issue> findByIsFinishedTrue();
        List<Issue> findByIsFinishedFalse();
        List<Issue> findByIsAcceptedTrueAndIsFinishedFalse();
        List<Issue> findByIsAcceptedFalse();

}
