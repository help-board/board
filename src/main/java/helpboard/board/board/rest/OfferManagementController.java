package helpboard.board.board.rest;

import helpboard.board.board.repository.CategoryRepository;
import helpboard.board.board.repository.OfferSearchRepository;
import helpboard.board.board.repository.VoivodeshipRepository;
import helpboard.board.board.rest.view.OfferDetailsDto;
import helpboard.board.board.rest.view.OfferDto;
import helpboard.board.board.service.OfferManagementService;
import helpboard.board.user.domain.UserPrincipal;
import java.net.URI;
import java.util.UUID;
import javax.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/offer/manage", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class OfferManagementController {

    OfferManagementService offerManagementService;
    OfferSearchRepository offerSearchRepository;
    CategoryRepository categoryRepository;
    VoivodeshipRepository voivodeshipRepository;

    @PostMapping
    @RolesAllowed("User")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal loggedUser,
                                    @RequestBody OfferDto offerDto) {
        var offer = offerManagementService.createOffer(loggedUser, offerDto);
        var category = categoryRepository.findById(offerDto.getCategoryId()).orElseThrow(this::notFound);
        var voivodeship = voivodeshipRepository.findById(offerDto.getVoivodeshipId()).orElseThrow(this::notFound);

        return ResponseEntity
                .created(URI.create("/offer/" + offer.getId()))
                .body(OfferDetailsDto.from(offer, category, voivodeship));
    }

    @GetMapping(value = "/list")
    @RolesAllowed("User")
    public ResponseEntity<?> list(@AuthenticationPrincipal UserPrincipal loggedUser, Pageable pageable) {
        final var result = offerSearchRepository.findByOwnerId(loggedUser.getUserId(), pageable);
        return ResponseEntity.ok(result);
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

    private RuntimeException notFound() {
        final var msg = "Not found";
        return new IllegalStateException(msg);
    }
}