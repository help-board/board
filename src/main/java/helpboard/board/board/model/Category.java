package helpboard.board.board.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Category {

    @Id
    private UUID id;
    private String title;
    private String description;
}
