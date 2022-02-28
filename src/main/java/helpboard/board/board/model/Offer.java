package helpboard.board.board.model;

import static com.google.common.base.Preconditions.checkState;
import helpboard.board.board.rest.view.OfferDto;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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

    @Column(name = "voivodeship_id")
    private Integer voivodeshipId;
    private String city;

    @Column(name = "free_space_from")
    private Integer freeSpaceFrom;
    @Column(name = "free_space_to")
    private Integer freeSpaceTo;
    private Boolean active = Boolean.FALSE;
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
        this.voivodeshipId = offerDto.getVoivodeshipId();
        this.city = offerDto.getCity();
        this.freeSpaceFrom = offerDto.getFreeSpaceFrom();
        this.freeSpaceTo = offerDto.getFreeSpaceTo();
        return this;
    }

    public Offer activate() {
        checkState(!isActivate(), "Offer already activate!");
        this.active = Boolean.TRUE;
        return this;
    }

    public Offer deactivate() {
        checkState(!isDeactivated(), "Offer already deactivate!");
        this.active = Boolean.FALSE;
        return this;
    }

    private Boolean isDeactivated() {
        return this.active == Boolean.FALSE;
    }

    private Boolean isActivate() {
        return this.active == Boolean.TRUE;
    }
}