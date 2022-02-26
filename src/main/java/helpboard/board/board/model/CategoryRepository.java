package helpboard.board.board.model;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    Optional<Category> findOne(UUID categoryId);

    Collection<Category> getAll();
}
