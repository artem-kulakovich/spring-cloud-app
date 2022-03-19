package by.bntu.fitr.accountservice.validator;


import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;
import by.bntu.fitr.accountservice.exception.ValidationException;
import org.springframework.stereotype.Component;


@Component
public class AccountValidator {

    private static class ValidationRule {
        private int userNameUpperLim = 100;
        private int userNameLowLim = 5;

        private int passwordUpperLim = 100;
        private int passwordLowLim = 5;

        private int emailUpperLim = 100;
        private int emailLowerLim = 10;


    }

    public boolean validate(AccountCreateDTO accountCreateDTO) {
        ValidationRule validationRule = new ValidationRule();
        StringBuilder errorMsg = new StringBuilder();
        boolean isValid = true;
        if (accountCreateDTO.getEmail() == null
                || !(accountCreateDTO.getEmail().length() >= validationRule.emailLowerLim
                && accountCreateDTO.getEmail().length() <= validationRule.emailUpperLim)) {
            isValid = false;
            errorMsg.append("email ...");
        }

        if (accountCreateDTO.getUserName() == null
                || !(accountCreateDTO.getUserName().length() >= validationRule.userNameLowLim
                && accountCreateDTO.getUserName().length() <= validationRule.userNameUpperLim)) {
            isValid = false;
            errorMsg.append("username ...");
        }

        if (accountCreateDTO.getPassword() == null
                || !(accountCreateDTO.getPassword().length() >= validationRule.passwordLowLim
                && accountCreateDTO.getPassword().length() <= validationRule.passwordUpperLim)) {
            isValid = false;
            errorMsg.append("password ...");
        }

        if (accountCreateDTO.getRepeatPassword() == null
                || !(accountCreateDTO.getRepeatPassword().length() >= validationRule.passwordLowLim
                && accountCreateDTO.getRepeatPassword().length() <= validationRule.passwordUpperLim)) {
            isValid = false;
            errorMsg.append("repeatPassword ...");
        }

        if (!isValid) {
            throw new ValidationException(errorMsg.toString());
        }

        return isValid;
    }
}
