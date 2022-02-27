package helpboard.board.common.rest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
class ErrorControllerImpl implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Object> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status instanceof Integer) {
            var statusCode = HttpStatus.valueOf((Integer) status);
            return createApiError(statusCode, statusCode.getReasonPhrase());
        }

        return createApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error");
    }

    private ResponseEntity<Object> createApiError(HttpStatus status, String message) {
        var dto = new ApiErrorDto();
        dto.setStatus(status.value());
        dto.setStatusMessage(status.name());
        dto.setMessage(message);
        return new ResponseEntity<>(dto, status);
    }
}
