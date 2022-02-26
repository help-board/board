package helpboard.board.user.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class User {

    @Id
    private UUID id;

    @Column
    private String username;

    @Column
    private String encryptedPassword;

    private User(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public static User create(String username) {
        var id = UUID.randomUUID();
        return new User(id, username);
    }

    public void changePassword(String encrytpedPassword) {
        this.encryptedPassword = encrytpedPassword;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
