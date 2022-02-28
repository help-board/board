package helpboard.board.board.rest;

import helpboard.board.board.repository.CategoryRepository;
import helpboard.board.board.repository.VoivodeshipRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dictionaries", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DictionaryController {

    VoivodeshipRepository voivodeshipRepository;
    CategoryRepository categoryRepository;

    @GetMapping(value = "/categories")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @GetMapping(value = "/voivodeships")
    public ResponseEntity<?> getVoivodeships() {
        return ResponseEntity.ok(voivodeshipRepository.findAll());
    }
}
