package FindWorkers.WorkersFinder.Users;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface RatingRepository extends MongoRepository<Rating, String> {
    List<Rating> findByWorkerId(String workerId);
    long countByWorkerId(String workerId);
    @Aggregation(pipeline = {
            "{ $match: { workerId: ?0 } }",
            "{ $group: { _id: null, totalRating: { $sum: '$Rating' } } }"
    })
    long sumRatingByWorkerId(String workerId);
}


