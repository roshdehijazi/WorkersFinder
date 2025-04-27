package FindWorkers.WorkersFinder;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface IssueRepository extends MongoRepository<Issue, String> {
}
