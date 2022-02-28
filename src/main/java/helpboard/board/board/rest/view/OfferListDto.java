package helpboard.board.board.rest.view;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferListDto {
    UUID id;
    String title;
    String description;
    String telephone;
    String contactName;
    String voivodeship;
    String city;
    Integer freeSpaceFrom;
    Integer freeSpaceTo;
    Boolean active;
    LocalDateTime created;
}