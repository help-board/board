package helpboard.board.user.service;

import helpboard.board.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class RegisterAccountServiceTest {

    @Autowired
    RegisterAccountService registerAccountService;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void cleanup() {
        userRepository.deleteAllInBatch();
    }

    @Test
    void registerNewAccount() {
        // given
        var username = "username";
        registerAccountService.register(username, "password");

        // then
        var found = userRepository.findOneByUsername(username);

        assertThat(found)
                .isPresent()
                .hasValueSatisfying(user -> {
                    assertThat(user.getUsername())
                            .isEqualTo(username);
                });
    }

    @Test
    void cannotRegisterIfUsernameAlreadyTaken() {
        // given
        var username = "username";
        registerAccountService.register(username, "password");

        assertThatCode(() -> registerAccountService.register(username, "password"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Username already taken");
    }
}
