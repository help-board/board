package helpboard.board.board.rest;

import helpboard.board.board.model.Offer;
import helpboard.board.board.rest.view.OfferListDto;
import helpboard.board.board.service.OfferManagementService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/offer", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class OfferSearchController {

    OfferManagementService offerManagementService;

    @GetMapping(value = "/{offerId}")
    public ResponseEntity<?> details(@PathVariable(value = "offerId") UUID offerId) {
        return ResponseEntity
                .ok(offerManagementService.details(offerId));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        return ResponseEntity
                .ok(toOfferListDto(offerManagementService.list()));
    }

    private List<OfferListDto> toOfferListDto(List<Offer> input) {
        return input.stream()
                .map(OfferListDto::from)
                .collect(Collectors.toList());
    }
}
