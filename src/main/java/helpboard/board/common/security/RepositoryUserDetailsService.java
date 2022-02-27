package helpboard.board.common.security;

import helpboard.board.user.domain.UserPrincipal;
import helpboard.board.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class RepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var found = userRepository.findOneByUsername(username);

        return found.map(UserPrincipal::createFrom)
                .orElseThrow(() -> notFound(username));
    }

    private UsernameNotFoundException notFound(String username) {
        return new UsernameNotFoundException(String.format("Username %s not found", username));
    }
}
