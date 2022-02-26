package helpboard.board.board.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class OfferDetails {
    private String title;
    private String description;
    @Embedded
    private Contact contact;
    @Embedded
    private Address address;
    @Column(name = "free_space")
    private Integer freeSpace;
}
