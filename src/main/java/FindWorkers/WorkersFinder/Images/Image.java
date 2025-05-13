package FindWorkers.WorkersFinder.Images;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "images")
@Data
public class Image {
    @Id
    private String id;
    private String name;
    private String description;
    private byte[] imageData;
    private String contentType;
}
