package by.bntu.fitr.accountservice.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @JsonProperty(value = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_id_seq", allocationSize = 1)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @JsonProperty(value = "firstName")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty(value = "lastName")
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty(value = "userName")
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @JsonProperty(value = "password")
    @Column(name = "password", nullable = false)
    private String password;

    @JsonProperty(value = "email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonProperty(value = "roleList")
    @ManyToMany()
    @JoinTable(
            name = "account_role"
            , joinColumns = @JoinColumn(name = "account_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id")
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

    public Account(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public void addRole(Role role) {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.add(role);
    }
}
