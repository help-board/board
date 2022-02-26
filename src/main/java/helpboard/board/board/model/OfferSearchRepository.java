package helpboard.board.board.model;

import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferSearchRepository {

    Collection<Offer> findAll();
}
