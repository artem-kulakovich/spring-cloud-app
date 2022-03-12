package by.bntu.fitr.accountservice.service.impl;

import by.bntu.fitr.accountservice.constant.RoleConstant;
import by.bntu.fitr.accountservice.entity.Account;
import by.bntu.fitr.accountservice.entity.Role;
import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;
import by.bntu.fitr.accountservice.exception.AccountAlreadyExistException;
import by.bntu.fitr.accountservice.exception.PasswordMismatchException;
import by.bntu.fitr.accountservice.repository.AccountRepository;
import by.bntu.fitr.accountservice.service.AccountService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(AccountCreateDTO accountCreateDTO) {
        if (!accountCreateDTO.getPassword().equals(accountCreateDTO.getRepeatPassword())) {
            throw new PasswordMismatchException("password dont mismatch");
        }

        if (accountRepository.findByUserName(accountCreateDTO.getUserName()).isPresent()) {
            throw new AccountAlreadyExistException("account already exist");
        }

        Account account = new Account(accountCreateDTO);
        account.addRole(new Role(RoleConstant.USER));
        accountRepository.save(account);
        return account;
    }


}
