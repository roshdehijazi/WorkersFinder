package FindWorkers.WorkersFinder.Images;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<String> uploadImage(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) throws IOException {

        Image image = new Image();
        image.setName(name);
        image.setDescription(description);
        image.setImageData(file.getBytes());
        image.setContentType(file.getContentType());

        imageRepository.save(image);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Image uploaded successfully with ID: " + image.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        Image image = imageRepository.findById(id).orElse(null);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .body(image.getImageData());
    }
}