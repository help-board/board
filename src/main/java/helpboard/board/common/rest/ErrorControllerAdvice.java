package helpboard.board.common.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
class ErrorControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDenied(
            Throwable ex) {
        return createApiError(UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            Throwable ex) {
        return createApiError(NOT_FOUND, ex.getMessage());
    }

    private ResponseEntity<Object> createApiError(HttpStatus status, String message) {
        var dto = new ApiErrorDto();
        dto.setStatus(status.value());
        dto.setStatusMessage(status.name());
        dto.setMessage(message);
        return new ResponseEntity<>(dto, status);
    }
}
