package helpboard.board.user.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    Optional<User> findOne(UUID userId);

    Optional<User> findByUsername(String username);

    boolean save(User user);
}
