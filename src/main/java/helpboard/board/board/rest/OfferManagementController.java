package helpboard.board.board.rest;

import helpboard.board.board.service.OfferManagementService;
import helpboard.board.board.rest.view.OfferDto;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
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
    public void create(@PathVariable(value = "userId") UUID userId, @RequestBody OfferDto offerDto) {
        offerManagementService.createOffer(userId, offerDto);
    }

    @PutMapping(value = "/{offerId}")
    public void update(@PathVariable(value = "offerId") UUID offerId, @RequestBody OfferDto offerDto) {
        offerManagementService.updateFreeSpace(offerId, offerDto);
    }

    @DeleteMapping(value = "/{offerId}")
    public void delete(@PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.delete(offerId);
    }

    @PutMapping(value = "/{offerId}/deactivate")
    public void deactivate(@PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.deactivate(offerId);
    }

    @PutMapping(value = "/{offerId}/activate")
    public void activate(@PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.activate(offerId);
    }
}
