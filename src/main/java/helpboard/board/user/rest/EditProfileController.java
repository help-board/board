package helpboard.board.user.rest;

import helpboard.board.user.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/user")
public class EditProfileController {

    @GetMapping
    @RolesAllowed("User")
    public String profileView(@AuthenticationPrincipal User user) {
        return "profile: " + user.getUsername();
    }
}
