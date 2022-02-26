package helpboard.board.board.rest.view;

import helpboard.board.board.model.Category;
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

    public static CategoryDto from(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }
}
