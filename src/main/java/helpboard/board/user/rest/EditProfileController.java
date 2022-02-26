package helpboard.board.user.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/user")
public class EditProfileController {

    @GetMapping
    @RolesAllowed("User")
    public String profileView(Authentication authentication) {
        return "profile: " + authentication.getPrincipal();
    }
}
