package by.bntu.fitr.notificationservice.exception;

public class CantSendEmailException extends RuntimeException {

    public CantSendEmailException(String msg) {
        super(msg);
    }
}
