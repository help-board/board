package helpboard.board.user.rest;

import helpboard.board.user.domain.UserPrincipal;
import helpboard.board.user.domain.UserRepository;
import helpboard.board.user.rest.view.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/user", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
class EditProfileController {

    private final UserRepository userRepository;

    @GetMapping
    @RolesAllowed("User")
    public ResponseEntity<?> profileView(@AuthenticationPrincipal UserPrincipal user) {
        var found = userRepository.findOneById(user.getUserId());
        return ResponseEntity.ok(UserProfileDto.from(found.get()));
    }
}
