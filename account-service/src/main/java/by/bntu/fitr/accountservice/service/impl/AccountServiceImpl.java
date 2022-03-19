package by.bntu.fitr.accountservice.service.impl;

import by.bntu.fitr.accountservice.component.PasswordEncoder;
import by.bntu.fitr.accountservice.constant.RoleConstant;
import by.bntu.fitr.accountservice.entity.Account;
import by.bntu.fitr.accountservice.entity.Role;
import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;
import by.bntu.fitr.accountservice.exception.AccountAlreadyExistExceptionException;
import by.bntu.fitr.accountservice.exception.AccountNotFoundException;
import by.bntu.fitr.accountservice.exception.EmailAlreadyExistsExceptionException;
import by.bntu.fitr.accountservice.exception.PasswordMismatchException;
import by.bntu.fitr.accountservice.repository.AccountRepository;
import by.bntu.fitr.accountservice.repository.RoleRepository;
import by.bntu.fitr.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
                              RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public Account addAccount(AccountCreateDTO accountCreateDTO) {
        if (!accountCreateDTO.getPassword().equals(accountCreateDTO.getRepeatPassword())) {
            throw new PasswordMismatchException("password dont mismatch");
        }

        if (accountRepository.findByUserName(accountCreateDTO.getUserName()).isPresent()) {
            throw new AccountAlreadyExistExceptionException("account already exist");
        }

        if (accountRepository.findByEmail(accountCreateDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsExceptionException("email already exist");
        }

        accountCreateDTO.setPassword(passwordEncoder.encode(accountCreateDTO.getPassword()));
        Account account = new Account(accountCreateDTO);
        account.addRole(roleRepository.findByName(RoleConstant.USER).get());
        accountRepository.save(account);
        return account;
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new AccountNotFoundException("account not found exception"));
    }

    @Override
    public boolean login(String userName, String password) {
        Optional<Account> accountOptional = accountRepository.findByUserName(userName);
        if (!accountOptional.isPresent()) {
            throw new AccountNotFoundException("account not found exception");
        }
        Account account = accountOptional.get();

        if (!passwordEncoder.encode(password).equals(account.getPassword())) {
            throw new PasswordMismatchException("password miss matches");
        }
        return true;
    }

    @Override
    public List<Role> getRoleByUserName(String userName) {
        Optional<Account> accountOptional = accountRepository.findByUserName(userName);
        if (!accountOptional.isPresent()) {
            throw new AccountNotFoundException("account not found exception");
        }
        Account account = accountOptional.get();
        return account.getRoleList();
    }


}
