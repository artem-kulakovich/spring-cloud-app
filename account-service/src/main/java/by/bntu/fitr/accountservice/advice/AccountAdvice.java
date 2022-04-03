package by.bntu.fitr.accountservice.advice;


import by.bntu.fitr.accountservice.constant.HttpStatusReasonConstant;
import by.bntu.fitr.accountservice.exception.*;
import by.bntu.fitr.accountservice.model.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class AccountAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<Response> handlePasswordMismatchException(PasswordMismatchException e) {
        Response response = new Response(HttpStatusReasonConstant.PASSWORD_MISMATCH, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Response> handleAlreadyExistException(AlreadyExistsException e) {
        Response response = null;
        if (e instanceof EmailAlreadyExistsExceptionException) {
            response = new Response(HttpStatusReasonConstant.EMAIL_ALREADY_EXISTS, e.getMessage());
        } else if (e instanceof AccountAlreadyExistExceptionException) {
            response = new Response(HttpStatusReasonConstant.USER_ALREADY_EXISTS, e.getMessage());
        } else {
            response = new Response(HttpStatusReasonConstant.ALREADY_EXISTS, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException e) {
        Response response = null;
        if (e instanceof AccountNotFoundException) {
            response = new Response(HttpStatusReasonConstant.ACCOUNT_NOT_FOUND, e.getMessage());
        } else {
            response = new Response(HttpStatusReasonConstant.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> handleValidationException(ValidationException e) {
        Response response = new Response(HttpStatusReasonConstant.BAD_VALIDATION, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
