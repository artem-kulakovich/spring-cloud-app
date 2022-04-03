package by.bntu.fitr.accountservice.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(String msg) {
        super(msg);
    }

}
