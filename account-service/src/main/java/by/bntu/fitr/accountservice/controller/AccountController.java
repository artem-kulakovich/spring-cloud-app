package by.bntu.fitr.accountservice.controller;

import by.bntu.fitr.accountservice.constant.JWTConstant;
import by.bntu.fitr.accountservice.entity.Account;
import by.bntu.fitr.accountservice.entity.Role;
import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;
import by.bntu.fitr.accountservice.entity.dto.JWTDTO;
import by.bntu.fitr.accountservice.feign.AuthenticationServiceClient;
import by.bntu.fitr.accountservice.service.AccountService;
import by.bntu.fitr.accountservice.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountValidator accountValidator;
    private final AuthenticationServiceClient authenticationServiceClient;

    @Autowired
    public AccountController(AccountService accountService, AccountValidator accountValidator,
                             AuthenticationServiceClient authenticationServiceClient) {
        this.accountService = accountService;
        this.accountValidator = accountValidator;
        this.authenticationServiceClient = authenticationServiceClient;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAccount(@RequestBody AccountCreateDTO accountCreateDTO) {
        accountValidator.validate(accountCreateDTO);
        Account account = accountService.addAccount(accountCreateDTO);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAccounts() {
        List<Account> accounts = accountService.getAccounts();
        if (accounts == null || accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccount(@PathVariable("id") Long id) {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody Map<String, String> params, HttpServletResponse httpServletResponse) {
        String userName = params.get("userName");
        String password = params.get("password");
        String token = null;
        if (accountService.login(userName, password)) {
            List<String> roleNames = accountService.getRoleByUserName(userName).stream()
                    .map(Role::getName).collect(Collectors.toList());
            JWTDTO jwtdto = new JWTDTO();
            jwtdto.setParams(JWTConstant.HS256, JWTConstant.JWT, userName, roleNames.toString());
            token = authenticationServiceClient.createJwt(jwtdto);
            httpServletResponse.setHeader("test", token);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);

    }
}
