package by.bntu.fitr.authenticationserver.advice;

import by.bntu.fitr.authenticationserver.exception.CantCreateJWTTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AuthenticationAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CantCreateJWTTokenException.class)
    public ResponseEntity<?> handleCantCreateJWTTokenException(CantCreateJWTTokenException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
