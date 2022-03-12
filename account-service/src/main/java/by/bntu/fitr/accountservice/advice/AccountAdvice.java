package by.bntu.fitr.accountservice.advice;


import by.bntu.fitr.accountservice.constant.HttpStatusReasonConstant;
import by.bntu.fitr.accountservice.exception.PasswordMismatchException;
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
}
