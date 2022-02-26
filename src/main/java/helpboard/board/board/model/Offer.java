package helpboard.board.board.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    private UUID id;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "category_id")
    private UUID categoryId;

    @Embedded
    private OfferDetails offerDetails;

    public Offer create(UUID ownerId) {
        UUID offerId = UUID.randomUUID();
        return new Offer();
        // once created call updateOfferDetails
    }

    public void updateOfferDetails(UUID category, String title, String description, String location, String contact, String contactName) {
        // ...
    }

    public void activate() {
        // ...
    }

    public void deactivate() {
        // ...
    }
}
