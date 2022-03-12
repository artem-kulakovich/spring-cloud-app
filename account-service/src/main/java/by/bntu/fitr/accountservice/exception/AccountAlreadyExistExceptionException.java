package by.bntu.fitr.accountservice.exception;

public class AccountAlreadyExistExceptionException extends AlreadyExistsException {
    public AccountAlreadyExistExceptionException(String msg) {
        super(msg);
    }
}
