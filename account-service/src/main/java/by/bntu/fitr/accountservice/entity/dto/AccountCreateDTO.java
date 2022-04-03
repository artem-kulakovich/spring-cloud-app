package by.bntu.fitr.accountservice.entity.dto;


import by.bntu.fitr.accountservice.constant.ValidationConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;


@ApiModel
@NoArgsConstructor
@Getter
@Setter
public class AccountCreateDTO {
    @Size(min = ValidationConstant.AccountConstraint.USER_NAME_MIN_SYMBOLS,
            max = ValidationConstant.AccountConstraint.USER_NAME_MAX_SYMBOLS,
            message = ValidationConstant.AccountErrorMessage.USER_NAME_VALIDATION_MESSAGE)
    @JsonProperty(value = "userName")
    private String userName;

    @Size(min = ValidationConstant.AccountConstraint.PASSWORD_MIN_SYMBOLS,
            max = ValidationConstant.AccountConstraint.PASSWORD_MAX_SYMBOLS,
            message = ValidationConstant.AccountErrorMessage.PASSWORD_VALIDATION_MESSAGE)
    @JsonProperty(value = "password")
    private String password;

    @Size(min = ValidationConstant.AccountConstraint.EMAIL_MIN_SYMBOLS,
            max = ValidationConstant.AccountConstraint.EMAIL_MAX_SYMBOLS,
            message = ValidationConstant.AccountErrorMessage.EMAIL_VALIDATION_MESSAGE)
    @JsonProperty(value = "email")
    private String email;

    @Size(min = ValidationConstant.AccountConstraint.PASSWORD_MIN_SYMBOLS,
            max = ValidationConstant.AccountConstraint.EMAIL_MAX_SYMBOLS,
            message = ValidationConstant.AccountErrorMessage.PASSWORD_VALIDATION_MESSAGE)
    @JsonProperty(value = "repeatPassword")
    private String repeatPassword;

    public AccountCreateDTO(String userName, String password, String email, String repeatPassword) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.repeatPassword = repeatPassword;
    }
}
