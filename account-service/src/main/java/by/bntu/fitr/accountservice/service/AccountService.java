package by.bntu.fitr.accountservice.service;

import by.bntu.fitr.accountservice.entity.Account;
import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;

import java.util.List;


public interface AccountService {
    Account addAccount(AccountCreateDTO accountCreateDTO);

    List<Account> getAccounts();

    Account getAccount(Long id);
}
