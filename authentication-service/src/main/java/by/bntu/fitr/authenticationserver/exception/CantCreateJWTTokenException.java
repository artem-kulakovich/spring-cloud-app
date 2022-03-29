package by.bntu.fitr.authenticationserver.exception;

public class CantCreateJWTTokenException extends RuntimeException {

    public CantCreateJWTTokenException(String msg) {
        super(msg);
    }
}
