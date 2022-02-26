package helpboard.board.board.rest;

import helpboard.board.board.rest.view.OfferDto;
import helpboard.board.board.service.OfferManagementService;
import helpboard.board.user.domain.UserPrincipal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/offer/manage", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferManagementController {

    OfferManagementService offerManagementService;

    @PostMapping(value = "/")
    @RolesAllowed("User")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal loggedUser,
                                    @RequestBody OfferDto offerDto) {
        offerManagementService.createOffer(loggedUser, offerDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/list")
    @RolesAllowed("User")
    public ResponseEntity<?> lis(@AuthenticationPrincipal UserPrincipal loggedUser) {
        // FIXME list user's offers ONLY!
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{offerId}")
    @RolesAllowed("User")
    public ResponseEntity<?> update(@AuthenticationPrincipal UserPrincipal loggedUser,
                                    @PathVariable(value = "offerId") UUID offerId,
                                    @RequestBody OfferDto offerDto) {
        offerManagementService.updateFreeSpace(loggedUser, offerId, offerDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{offerId}")
    @RolesAllowed("User")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserPrincipal loggedUser,
                                    @PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.delete(loggedUser, offerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{offerId}/deactivate")
    @RolesAllowed("User")
    public ResponseEntity<?> deactivate(@AuthenticationPrincipal UserPrincipal loggedUser,
                                        @PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.deactivate(loggedUser, offerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{offerId}/activate")
    @RolesAllowed("User")
    public ResponseEntity<?> activate(@AuthenticationPrincipal UserPrincipal loggedUser,
                                      @PathVariable(value = "offerId") UUID offerId) {
        offerManagementService.activate(loggedUser, offerId);
        return ResponseEntity.ok().build();
    }
}
