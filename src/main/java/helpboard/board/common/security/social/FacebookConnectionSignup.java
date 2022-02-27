package helpboard.board.common.security.social;

import helpboard.board.user.domain.User;
import helpboard.board.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        var connectionKey = connection.getKey();
        User user = User.createExternalUser(connectionKey.getProviderId(), connectionKey.getProviderUserId());

        user.updateProfile(connection.getDisplayName(), connection.getImageUrl(), connection.getProfileUrl());

        userRepository.save(user);
        return user.getId().toString();
    }
}
