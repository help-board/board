package helpboard.board.board.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Offer {

    @Id
    private UUID id;
    private UUID ownerId;

    private UUID category;

    private String title;
    private String description;
    private String location;

    private String contact;
    private String contactName;

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
