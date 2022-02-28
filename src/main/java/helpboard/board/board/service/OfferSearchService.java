package helpboard.board.board.service;

import helpboard.board.board.model.Category;
import helpboard.board.board.repository.CategoryRepository;
import helpboard.board.board.model.Offer;
import helpboard.board.board.repository.OfferRepository;
import helpboard.board.board.repository.OfferSearchRepository;
import helpboard.board.board.model.Voivodeship;
import helpboard.board.board.repository.VoivodeshipRepository;
import helpboard.board.board.rest.view.OfferDetailsDto;
import helpboard.board.board.rest.view.OfferListDto;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferSearchService {

    OfferRepository offerRepository;
    OfferSearchRepository offerSearchRepository;
    CategoryRepository categoryRepository;
    VoivodeshipRepository voivodeshipRepository;

    public OfferDetailsDto details(UUID offerId) {
        final var offer = loadOffer(offerId);
        final var category = loadCategory(offer.getCategoryId());
        var voivodeship = loadVoivodeship(offer.getVoivodeshipId());

        return OfferDetailsDto.from(offer, category, voivodeship);
    }

    public Page<OfferListDto> search(UUID categoryId, Integer voivodeshipId, Integer persons, Pageable pageable) {
        return offerSearchRepository.search(categoryId, voivodeshipId, persons, pageable);
    }

    private Offer loadOffer(UUID offerId) {
        return offerRepository.findById(offerId).orElseThrow(() -> offerNotFound(offerId));
    }

    private Category loadCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(this::notFound);
    }

    private Voivodeship loadVoivodeship(Integer voivodeshipId) {
        return voivodeshipRepository.findById(voivodeshipId).orElseThrow(this::notFound);
    }

    private RuntimeException offerNotFound(UUID offerId) {
        final var msg = "Not found offer with id: " + offerId;
        return new IllegalStateException(msg);
    }

    private RuntimeException notFound() {
        final var msg = "Not found";
        return new IllegalStateException(msg);
    }
}