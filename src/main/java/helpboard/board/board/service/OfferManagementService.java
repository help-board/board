package helpboard.board.board.service;

import helpboard.board.board.model.Offer;
import helpboard.board.board.repository.OfferRepository;
import helpboard.board.board.rest.view.OfferDto;
import helpboard.board.user.domain.UserPrincipal;
import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferManagementService {

    OfferRepository offerRepository;

    public Offer createOffer(UserPrincipal loggedUser, OfferDto offerDto) {
        var offer = Offer.create(loggedUser.getUserId())
                .updateOfferDetails(offerDto)
                .activate();
        return offerRepository.save(offer);
    }

    public void updateFreeSpace(UserPrincipal loggedUser, UUID offerId, OfferDto offerDto) {
        var offer = loadOffer(offerId);
        checkOwnership(offer, loggedUser);
        offerRepository.save(offer.updateOfferDetails(offerDto));
    }

    public void delete(UserPrincipal loggedUser, UUID offerId) {
        final var offer = loadOffer(offerId);
        checkOwnership(offer, loggedUser);
        offerRepository.delete(offer);
    }

    public void deactivate(UserPrincipal loggedUser, UUID offerId) {
        var offer = loadOffer(offerId);
        checkOwnership(offer, loggedUser);
        offer.deactivate();
        offerRepository.save(offer);
    }

    public void activate(UserPrincipal loggedUser, UUID offerId) {
        var offer = loadOffer(offerId);
        checkOwnership(offer, loggedUser);
        offer.activate();
        offerRepository.save(offer);
    }

    private void checkOwnership(Offer offer, UserPrincipal loggedUser) {
        if (!Objects.equals(offer.getOwnerId(), loggedUser.getUserId())) {
            throw new IllegalStateException("Not permitted");
        }
    }

    private Offer loadOffer(UUID offerId) {
        return offerRepository.findById(offerId).orElseThrow(() -> offerNotFound(offerId));
    }

    private RuntimeException offerNotFound(UUID offerId) {
        final var msg = "Not found offer with id: " + offerId;
        return new IllegalStateException(msg);
    }
}