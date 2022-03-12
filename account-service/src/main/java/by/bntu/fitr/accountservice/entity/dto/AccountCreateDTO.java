package by.bntu.fitr.accountservice.entity.dto;

import by.bntu.fitr.accountservice.entity.Account;
import by.bntu.fitr.accountservice.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
public class AccountCreateDTO {

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty(value = "userName")
    private String userName;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "repeatPassword")
    private String repeatPassword;

    @JsonProperty(value = "roleList")
    private List<Role> roleList;

    public AccountCreateDTO(Account account, String repeatPassword) {
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.password = account.getPassword();
        this.userName = account.getUserName();
        this.email = account.getEmail();
        this.roleList = account.getRoleList();
        this.repeatPassword = repeatPassword;
    }
}
