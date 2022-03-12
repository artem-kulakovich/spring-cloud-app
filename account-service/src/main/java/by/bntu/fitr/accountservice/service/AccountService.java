package by.bntu.fitr.accountservice.service;

import by.bntu.fitr.accountservice.entity.Account;
import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AccountService {
    Account addAccount(AccountCreateDTO accountCreateDTO);

}
