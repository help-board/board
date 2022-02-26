package helpboard.board.board.rest;

import helpboard.board.board.rest.view.OfferDto;
import helpboard.board.board.service.OfferManagementService;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/offer/manage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferManagementController {

    OfferManagementService offerManagementService;

    @PostMapping(value = "/{userId}")
    public ResponseEntity<?> create(@PathVariable(value = "userId") UUID userId, @RequestBody OfferDto offerDto) {
        offerManagementService.createOffer(userId, offerDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{offerId}")
    public ResponseEntity<?> update(@PathVariable(value = "offerId") UUID offerId, @RequestBody OfferDto offerDto) {
        offerManagementService.updateFreeSpace(offerId, offerDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{offerId}")
    public ResponseEntity<?> delete(@PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.delete(offerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{offerId}/deactivate")
    public ResponseEntity<?> deactivate(@PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.deactivate(offerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{offerId}/activate")
    public ResponseEntity<?> activate(@PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.activate(offerId);
        return ResponseEntity.ok().build();
    }
}
