package by.bntu.fitr.accountservice.constant;

public interface ValidationConstant {

    interface AccountErrorMessage {
        String USER_NAME_VALIDATION_MESSAGE = "userName should more than 5 and less than 100 symbols";
        String PASSWORD_VALIDATION_MESSAGE = "password should more than 5 and less than 1024 symbols";
        String EMAIL_VALIDATION_MESSAGE = "email should more than 10 and less than 255 symbols";
    }

    interface AccountConstraint {
        int USER_NAME_MIN_SYMBOLS = 5;
        int USER_NAME_MAX_SYMBOLS = 100;

        int PASSWORD_MIN_SYMBOLS = 5;
        int PASSWORD_MAX_SYMBOLS = 1024;

        int EMAIL_MIN_SYMBOLS = 10;
        int EMAIL_MAX_SYMBOLS = 255;
    }
}
