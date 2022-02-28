package helpboard.board.board.repository;

import helpboard.board.board.model.Voivodeship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Integer> {

}
