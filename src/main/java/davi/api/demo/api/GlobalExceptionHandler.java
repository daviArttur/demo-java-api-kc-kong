package davi.api.demo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle specific exception
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
//        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
//    }

    // Handle validation errors
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleValidationErrors(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(err ->
//                errors.put(err.getField(), err.getDefaultMessage())
//        );
        return buildResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    // Handle generic exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
//        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
//    }
//
//    // Helper to create consistent responses
//    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
//        return buildResponse(status, message, null);
//    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message, Object details) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        if (details != null) {
            body.put("details", details);
        }
        return new ResponseEntity<>(body, status);
    }
}
