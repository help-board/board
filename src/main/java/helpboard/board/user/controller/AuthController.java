package helpboard.board.user.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/success")
    public Object loginSuccess() {
        return "ok";
        // FIXME
        //return ResponseEntity.ok(AuthResult.builder().status(true));
    }

    @PostMapping("/failure")
    public Object loginFailure() {
        return "fail";
        // FIXME
        //return ResponseEntity.ok(AuthResult.builder().status(false).message("failed"));
    }

    @Data
    @Builder
    private static class AuthResult {
        boolean status;
        String message;
    }
}
