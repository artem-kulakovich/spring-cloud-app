package by.bntu.fitr.accountservice.controller;

import by.bntu.fitr.accountservice.entity.Account;
import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;
import by.bntu.fitr.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAccount(@RequestBody AccountCreateDTO accountCreateDTO) {
        Account account = accountService.addAccount(accountCreateDTO);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}
