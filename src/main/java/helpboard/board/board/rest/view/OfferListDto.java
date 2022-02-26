package helpboard.board.board.rest.view;

import helpboard.board.board.model.Offer;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferListDto {
    UUID id;
    String title;
    String description;
    String telephone;
    String contactName;
    String location;
    Integer freeSpace;
    Boolean active;
    LocalDateTime created;

    public static OfferListDto from(Offer offer) {
        return OfferListDto.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .telephone(offer.getTelephone())
                .contactName(offer.getContactName())
                .location(offer.getLocation())
                .freeSpace(offer.getFreeSpace())
                .active(offer.getActive())
                .created(offer.getCreated())
                .build();
    }
}
