package helpboard.board.board.service;

import helpboard.board.board.model.Category;
import helpboard.board.board.model.CategoryRepository;
import helpboard.board.board.model.Offer;
import helpboard.board.board.model.OfferRepository;
import helpboard.board.board.model.OfferSearchRepository;
import helpboard.board.board.rest.view.OfferDetailsDto;
import helpboard.board.board.rest.view.OfferDto;
import helpboard.board.board.rest.view.OfferListDto;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import helpboard.board.user.domain.UserPrincipal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferManagementService {

    OfferRepository offerRepository;
    OfferSearchRepository offerSearchRepository;
    CategoryRepository categoryRepository;

    public Offer createOffer(UserPrincipal loggedUser, OfferDto offerDto) {
        var offer = Offer.create(loggedUser.getUserId())
                .updateOfferDetails(offerDto)
                .activate();
        return offerRepository.save(offer);
    }

    public OfferDetailsDto details(UUID offerId) {
        final var offer = loadOffer(offerId);
        final var category = loadCategory(offer.getCategoryId());
        return OfferDetailsDto.from(offer, category);
    }

    public List<Offer> list() {
        return offerSearchRepository.findAll();
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

    private Category loadCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> categoryNotFound(categoryId));
    }

    private RuntimeException offerNotFound(UUID offerId) {
        final var msg = "Not found offer with id: " + offerId;
        return new IllegalStateException(msg);
    }

    private RuntimeException categoryNotFound(UUID categoryId) {
        final var msg = "Not found category with id: " + categoryId;
        return new IllegalStateException(msg);
    }
}