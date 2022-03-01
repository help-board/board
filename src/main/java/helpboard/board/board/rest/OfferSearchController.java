package helpboard.board.board.rest;

import helpboard.board.board.service.OfferSearchService;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/offer")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class OfferSearchController {

    OfferSearchService offerSearchService;

    @GetMapping(value = "/{offerId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> details(@PathVariable(value = "offerId") UUID offerId) {
        return ResponseEntity
                .ok(offerSearchService.details(offerId));
    }

    @GetMapping(value = "/list", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(@RequestParam(name = "categoryId") UUID categoryId,
                                  @RequestParam(name = "voivodeshipId", required = false) Integer voivodeshipId,
                                  @RequestParam(name = "persons", required = false) Integer persons,
                                  Pageable pageable) {
        var response = offerSearchService.search(categoryId, voivodeshipId, persons, pageable);
        return ResponseEntity.ok(response);
    }
}
