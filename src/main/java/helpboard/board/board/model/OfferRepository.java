package helpboard.board.board.model;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository {

    Optional<Offer> findOne(UUID offerId);

    boolean save(Offer offer);

    boolean remove(Offer offer);
}
