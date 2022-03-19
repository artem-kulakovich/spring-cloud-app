package by.bntu.fitr.frontend.controller;


import by.bntu.fitr.frontend.dto.feign.AccountCreateDto;
import by.bntu.fitr.frontend.feign.AccountServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping(value = "/task-tracker")
public class AccountController {
    private final AccountServiceClient accountServiceClient;

    @Autowired
    public AccountController(AccountServiceClient accountServiceClient) {
        this.accountServiceClient = accountServiceClient;
    }

    @GetMapping(value = "/registration")
    public String getRegisterForm() {
        return "registration";
    }

    @GetMapping(value = "/login")
    public String getLoginView() {
        return "login";
    }

    @PostMapping(value = "/registration")
    public String addAccount(@RequestParam("userName") String userName,
                             @RequestParam("password") String password,
                             @RequestParam("repeatPassword") String repeatPassword,
                             @RequestParam("email") String email) {
        accountServiceClient.saveAccount(new AccountCreateDto(password, userName, repeatPassword, email));
        return "redirect:/login";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(@RequestParam Map<String, String> params,
                        HttpServletRequest httpServletRequest) {

        accountServiceClient.login(params);
        System.out.println(httpServletRequest.getHeader("Authenticated"));
        return "redirect:/registration";
    }


}
