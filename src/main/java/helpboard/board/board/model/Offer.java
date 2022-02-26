package helpboard.board.board.model;

import helpboard.board.board.rest.view.OfferDetailsDto;
import helpboard.board.board.rest.view.OfferDto;
import helpboard.board.board.rest.view.OfferListDto;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "offers")
public class Offer {

    @Id
    private UUID id;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "category_id")
    private UUID categoryId;

    private String title;
    private String description;
    private String telephone;

    @Column(name = "contact_name")
    private String contactName;
    private String location;

    @Column(name = "free_space")
    private Integer freeSpace;
    private Boolean active;
    private LocalDateTime created;

    private Offer(UUID id, UUID ownerId, LocalDateTime created) {
        this.id = id;
        this.ownerId = ownerId;
        this.created = created;
    }

    public static Offer create(UUID ownerId) {
        UUID offerId = UUID.randomUUID();
        return new Offer(offerId, ownerId, LocalDateTime.now());
    }

    public Offer updateOfferDetails(OfferDto offerDto) {
        this.categoryId = offerDto.getCategoryId();
        this.title = offerDto.getTitle();
        this.description = offerDto.getDescription();
        this.telephone = offerDto.getTelephone();
        this.contactName = offerDto.getContactName();
        this.location = offerDto.getLocation();
        this.freeSpace = offerDto.getFreeSpace();
        return this;
    }

    public Offer activate() {
        this.active = Boolean.TRUE;
        return this;
    }

    public Offer deactivate() {
        this.active = Boolean.FALSE;
        return this;
    }

    public OfferDetailsDto toOfferDetailsDto(Category category) {
        return OfferDetailsDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .telephone(this.telephone)
                .contactName(this.contactName)
                .location(this.location)
                .freeSpace(this.freeSpace)
                .active(this.active)
                .created(this.created)
                .category(category)
                .build();
    }

    public OfferListDto toOfferListDto() {
        return OfferListDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .telephone(this.telephone)
                .contactName(this.contactName)
                .location(this.location)
                .freeSpace(this.freeSpace)
                .active(this.active)
                .created(this.created)
                .build();
    }
}
