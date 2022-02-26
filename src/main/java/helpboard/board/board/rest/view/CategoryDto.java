package helpboard.board.board.rest.view;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryDto {
    UUID id;
    String title;
    String description;
}
