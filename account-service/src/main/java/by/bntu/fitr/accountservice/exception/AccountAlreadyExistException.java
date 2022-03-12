package by.bntu.fitr.accountservice.exception;

public class AccountAlreadyExistException extends RuntimeException {
    public AccountAlreadyExistException(String msg) {
        super(msg);
    }
}
