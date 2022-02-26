package helpboard.board.user.rest;

import helpboard.board.user.rest.view.LoginResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/auth", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AuthController {

    @PostMapping("/success")
    public ResponseEntity<?> loginSuccess() {
        return ResponseEntity.ok(LoginResultDto.builder().result(true).build());
    }

    @PostMapping("/failure")
    public ResponseEntity<?> loginFailure(HttpServletRequest request) {
        return ResponseEntity.ok(LoginResultDto.builder().result(false).message(loginErrorMessage(request)).build());
    }

    private String loginErrorMessage(HttpServletRequest request) {
        Object ex = request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (ex instanceof AuthenticationException) {
            return ((AuthenticationException) ex).getMessage();
        }

        return null;
    }
}
