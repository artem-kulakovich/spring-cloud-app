package by.bntu.fitr.accountservice.advice;


import by.bntu.fitr.accountservice.constant.HttpStatusReasonConstant;
import by.bntu.fitr.accountservice.exception.*;
import by.bntu.fitr.accountservice.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
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

}
