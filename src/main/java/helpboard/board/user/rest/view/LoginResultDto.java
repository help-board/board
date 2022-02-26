package helpboard.board.user.rest.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResultDto {
    private boolean result;
    private String message;
}
