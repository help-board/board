package helpboard.board.board.model;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository {

    Optional<Offer> findOne(UUID offerId);
    boolean save(Offer offer);
    boolean remove(Offer offer);
}
