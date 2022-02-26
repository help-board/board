package helpboard.board.user.service;

import helpboard.board.user.domain.Role;
import helpboard.board.user.domain.User;
import helpboard.board.user.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class RepositoryUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var found = userRepository.findOneByUsername(username);

        return found.map(this::toUserDetails)
                .orElseThrow(() -> notFound(username));
    }

    private UsernameNotFoundException notFound(String username) {
        return new UsernameNotFoundException(String.format("Username %s not found", username));
    }

    private UserDetails toUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getEncryptedPassword(), authorities());
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return List.of(new SimpleGrantedAuthority(Role.User.toString()));
    }
}
