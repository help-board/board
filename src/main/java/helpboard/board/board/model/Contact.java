package helpboard.board.board.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {
    private String telephone;

    @Column(name = "contact_name")
    private String name;
}
