package helpboard.board.board.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "voivodeship")
public class Voivodeship {

    @Id
    private Integer id;
    private String value;
}
