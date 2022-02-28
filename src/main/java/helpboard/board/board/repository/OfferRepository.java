package helpboard.board.board.repository;

import helpboard.board.board.model.Offer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {

}
