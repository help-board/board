package helpboard.board.board.rest.view;

import helpboard.board.board.model.Category;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferDetailsDto {
    UUID id;
    String title;
    String description;
    String telephone;
    String contactName;
    String location;
    Integer freeSpace;
    Boolean active;
    LocalDateTime created;
    Category category;
}
