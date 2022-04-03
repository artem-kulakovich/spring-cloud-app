package by.bntu.fitr.accountservice.entity;


import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "account entity")
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @ApiModelProperty(notes = "account id", required = true, position = 0)
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @ApiModelProperty(notes = "first name of account", required = false, position = 4)
    @JsonProperty(value = "firstName")
    @Column(name = "first_name")
    private String firstName;

    @ApiModelProperty(notes = "last name of account", required = false, position = 5)
    @JsonProperty(value = "lastName")
    @Column(name = "last_name")
    private String lastName;

    @ApiModelProperty(notes = "user name of account", required = true, position = 1)
    @JsonProperty(value = "userName")
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @ApiModelProperty(notes = "password of account", required = true, position = 2)
    @JsonProperty(value = "password")
    @Column(name = "password", nullable = false)
    private String password;

    @ApiModelProperty(notes = "email of account", required = true, position = 3)
    @JsonProperty(value = "email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ApiModelProperty(notes = "roles of account", required = true, position = 6)
    @JsonProperty(value = "roleList")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Setter(value = AccessLevel.PRIVATE)
    private List<Role> roleList;

    public Account(String firstName, String lastName, String userName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Account(AccountCreateDTO accountCreateDTO) {
        this.userName = accountCreateDTO.getUserName();
        this.email = accountCreateDTO.getEmail();
        this.password = accountCreateDTO.getPassword();
    }

    public void addRole(Role role) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.add(role);
    }
}
