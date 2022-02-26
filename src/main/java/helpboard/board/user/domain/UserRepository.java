package helpboard.board.user.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findOne(UUID userId);
}
