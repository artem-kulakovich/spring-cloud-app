package by.bntu.fitr.accountservice.controller;

import by.bntu.fitr.accountservice.constant.JWTConstant;
import by.bntu.fitr.accountservice.entity.Account;
import by.bntu.fitr.accountservice.entity.Role;
import by.bntu.fitr.accountservice.entity.dto.AccountCreateDTO;
import by.bntu.fitr.accountservice.entity.dto.JWTDTO;
import by.bntu.fitr.accountservice.feign.rest.AuthenticationServiceClient;
import by.bntu.fitr.accountservice.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "account-service", description = "account management operations")
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AuthenticationServiceClient authenticationServiceClient;

    @Autowired
    public AccountController(AccountService accountService,
                             AuthenticationServiceClient authenticationServiceClient) {
        this.accountService = accountService;
        this.authenticationServiceClient = authenticationServiceClient;
    }

    @ApiOperation(value = "registrate new user", response = Account.class)
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAccount(@RequestBody @Valid AccountCreateDTO accountCreateDTO) {
        Account account = accountService.addAccount(accountCreateDTO);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @ApiOperation(value = "get all users", response = Account[].class)
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAccounts() {
        List<Account> accounts = accountService.getAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @ApiOperation(value = "get account by account id", response = Account.class)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAccount(@PathVariable("id") Long id) {
        Account account = accountService.getAccount(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @ApiOperation(value = "login user", response = String.class)
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody Map<String, String> params) {
        String userName = params.get("userName");
        String password = params.get("password");
        String token = null;
        if (accountService.login(userName, password)) {
            List<String> roleNames = accountService.getRoleByUserName(userName).stream()
                    .map(Role::getName).collect(Collectors.toList());
            JWTDTO jwtdto = new JWTDTO();
            jwtdto.setParams(JWTConstant.HS256, JWTConstant.JWT, userName, roleNames.toString());
            token = authenticationServiceClient.createJwt(jwtdto);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
