package by.bntu.fitr.frontend.controller;


import by.bntu.fitr.frontend.dto.feign.AccountCreateDto;
import by.bntu.fitr.frontend.feign.AccountServiceClient;
import by.bntu.fitr.frontend.wrapper.AccountServiceClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;


@Controller
@RequestMapping(value = "/task-tracker")
public class AccountController {
    private final AccountServiceClientWrapper accountServiceClientWrapper;

    @Autowired
    public AccountController(AccountServiceClientWrapper accountServiceClientWrapper) {
        this.accountServiceClientWrapper = accountServiceClientWrapper;
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
        accountServiceClientWrapper.saveAccount(new AccountCreateDto(password, userName, repeatPassword, email));
        return "redirect:/login";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(@RequestParam Map<String, String> params, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        String token = accountServiceClientWrapper.login(params);
        System.out.println(token);
        httpServletResponse.addCookie(new Cookie("ishopJWT", token));
        return "redirect:/registration";
    }


}
