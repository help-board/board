package helpboard.board.user.rest;

import helpboard.board.user.domain.User;
import helpboard.board.user.rest.view.RegisterAccountDto;
import helpboard.board.user.rest.view.UserProfileDto;
import helpboard.board.user.service.RegisterAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegisterAccountController {

    private final RegisterAccountService registerAccountService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterAccountDto request) {
        return toResponse(registerAccountService.register(request.getUsername(), request.getPassword()));
    }

    private ResponseEntity<?> toResponse(User created) {
        return ResponseEntity
                .created(URI.create("/user"))
                .body(UserProfileDto.from(created));
    }
}
