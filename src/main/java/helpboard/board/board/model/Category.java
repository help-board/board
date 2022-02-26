package helpboard.board.board.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
@Table(name = "categories")
public class Category {

    @Id
    private UUID id;
    private String title;
    private String description;
}
