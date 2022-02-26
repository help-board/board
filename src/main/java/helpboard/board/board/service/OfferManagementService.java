package helpboard.board.board.service;

import helpboard.board.board.model.Category;
import helpboard.board.board.model.CategoryRepository;
import helpboard.board.board.model.Offer;
import helpboard.board.board.model.OfferRepository;
import helpboard.board.board.model.OfferSearchRepository;
import helpboard.board.board.view.OfferDetailsDto;
import helpboard.board.board.view.OfferDto;
import helpboard.board.board.view.OfferListDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferManagementService {

    OfferRepository offerRepository;
    OfferSearchRepository offerSearchRepository;
    CategoryRepository categoryRepository;

    public void createOffer(UUID userId, OfferDto offerDto) {
        var offer = Offer.create(userId)
                .updateOfferDetails(offerDto)
                .activate();
        offerRepository.save(offer);
    }

    public OfferDetailsDto details(UUID offerId) {
        final var offer = loadOffer(offerId);
        final var category = loadCategory(offer.getCategoryId());
        return offer.toOfferDetailsDto(category);
    }

    public List<OfferListDto> list() {
        return offerSearchRepository.findAll().stream()
                .map(Offer::toOfferListDto)
                .collect(Collectors.toList());
    }

    public void updateFreeSpace(UUID offerId, OfferDto offerDto) {
        var offer = loadOffer(offerId);
        offerRepository.save(offer.updateOfferDetails(offerDto));
    }

    public void delete(UUID offerId) {
        final var offer = loadOffer(offerId);
        offerRepository.remove(offer);
    }

    public void deactivate(UUID offerId) {
        var offer = loadOffer(offerId);
        offer.deactivate();
        offerRepository.save(offer);
    }

    public void activate(UUID offerId) {
        var offer = loadOffer(offerId);
        offer.activate();
        offerRepository.save(offer);
    }

    private Offer loadOffer(UUID offerId) {
        return offerRepository.findOne(offerId).orElseThrow(() -> offerNotFound(offerId));
    }

    private Category loadCategory(UUID categoryId) {
        return categoryRepository.findOne(categoryId).orElseThrow(() -> categoryNotFound(categoryId));
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