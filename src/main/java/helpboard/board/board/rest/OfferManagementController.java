package helpboard.board.board.rest;

import helpboard.board.board.model.CategoryRepository;
import helpboard.board.board.model.Offer;
import helpboard.board.board.model.OfferSearchRepository;
import helpboard.board.board.rest.view.OfferDetailsDto;
import helpboard.board.board.rest.view.OfferDto;
import helpboard.board.board.rest.view.OfferListDto;
import helpboard.board.board.service.OfferManagementService;
import helpboard.board.user.domain.UserPrincipal;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class OfferManagementController {

    OfferManagementService offerManagementService;
    OfferSearchRepository offerSearchRepository;
    CategoryRepository categoryRepository;

    @PostMapping(value = "/")
    @RolesAllowed("User")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal loggedUser,
                                    @RequestBody OfferDto offerDto) {
        var offer = offerManagementService.createOffer(loggedUser, offerDto);
        var category = categoryRepository.findById(offerDto.getCategoryId())
                .orElseThrow(() -> categoryNotFound(offerDto.getCategoryId()));
        return ResponseEntity
                .created(URI.create("/offer/"+offer.getId()))
                .body(OfferDetailsDto.from(offer, category));
    }

    @PostMapping(value = "/list")
    @RolesAllowed("User")
    public ResponseEntity<?> list(@AuthenticationPrincipal UserPrincipal loggedUser) {
        final var result = toOfferListDto(offerSearchRepository.findByOwnerId(loggedUser.getUserId()));
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

    private List<OfferListDto> toOfferListDto(List<Offer> input) {
        return input.stream()
                .map(OfferListDto::from)
                .collect(Collectors.toList());
    }

    private RuntimeException categoryNotFound(UUID categoryId) {
        final var msg = "Not found category with id: " + categoryId;
        return new IllegalStateException(msg);
    }
}