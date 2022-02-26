package helpboard.board.user.service;

import helpboard.board.user.domain.User;
import helpboard.board.user.domain.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterAccountService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(String username, String password) {
        if (isUsernameAlreadyTaken(username)) {
            throw new IllegalArgumentException("Username already taken");
        }

        var user = User.create(username);
        user.changePassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);

        return user;
    }

    public boolean isUsernameAlreadyTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
