package helpboard.board.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findOneById(UUID userId);

    Optional<User> findOneByUsername(String username);

//    boolean save(User user);
}
