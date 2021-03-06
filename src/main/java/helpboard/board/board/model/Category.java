package helpboard.board.board.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "categories")
public class Category {

    @Id
    private UUID id;
    private String title;
    private String description;
}
