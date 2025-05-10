package FindWorkers.WorkersFinder;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface offerRepository  extends MongoRepository<offer, String> {
}
