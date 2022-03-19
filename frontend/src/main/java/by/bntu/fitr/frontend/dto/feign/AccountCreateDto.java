package by.bntu.fitr.frontend.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class AccountCreateDto {
    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "userName")
    private String userName;

    @JsonProperty(value = "repeatPassword")
    private String repeatPassword;

    @JsonProperty(value = "email")
    private String email;


    public AccountCreateDto(String password, String userName, String repeatPassword, String email) {
        this.password = password;
        this.userName = userName;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }
}
