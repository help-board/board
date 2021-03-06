package helpboard.board.board.rest.view;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OfferDto {
    UUID categoryId;
    String title;
    String description;
    String telephone;
    String contactName;
    Integer voivodeshipId;
    String city;
    Integer freeSpaceFrom;
    Integer freeSpaceTo;
}