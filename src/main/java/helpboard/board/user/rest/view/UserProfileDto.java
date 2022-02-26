package helpboard.board.user.rest.view;

import helpboard.board.user.domain.User;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserProfileDto {

    private UUID id;
    private String username;

    public static UserProfileDto from(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
