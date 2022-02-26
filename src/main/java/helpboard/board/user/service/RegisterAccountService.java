package helpboard.board.user.service;

import helpboard.board.user.domain.User;
import helpboard.board.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String username, String password) {
        if (isUsernameAlreadyTaken(username)) {
            throw new IllegalArgumentException("Username already taken");
        }

        var user = User.create(username);
        user.changePassword(passwordEncoder.encode(password));

        userRepository.save(user);

        return user;
    }

    public boolean isUsernameAlreadyTaken(String username) {
        return userRepository.findOneByUsername(username).isPresent();
    }
}
