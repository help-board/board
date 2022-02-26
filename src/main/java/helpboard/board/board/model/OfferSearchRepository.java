package helpboard.board.board.model;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferSearchRepository extends JpaRepository<Offer, UUID> {

    List<Offer> findAll();

    List<Offer> findByOwnerId(UUID ownerId);
}
