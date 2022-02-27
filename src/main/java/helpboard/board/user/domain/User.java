package helpboard.board.user.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    private UUID id;

    @Column
    private String username;

    @Column(name = "password")
    private String encryptedPassword;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "provider_user_id")
    private String providerUserId;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "profile_url")
    private String profileUrl;

    private User(UUID id, String username, String providerId, String providerUserId) {
        this.id = id;
        this.username = username;
        this.providerId = providerId;
        this.providerUserId = providerUserId;
    }

    public static User create(String username) {
        var id = UUID.randomUUID();
        return new User(id, username, null, null);
    }

    public static User createExternalUser(String providerId, String providerUserId) {
        var id = UUID.randomUUID();
        var syntheticUsername = String.format("provider:%s:%s", providerId, providerUserId);
        return new User(id, syntheticUsername, providerId, providerUserId);
    }

    public void changePassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void updateProfile(String displayName, String imageUrl, String profileUrl) {
        this.displayName = displayName;
        this.imageUrl = imageUrl;
        this.profileUrl = profileUrl;
    }
}
