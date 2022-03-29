package by.bntu.fitr.frontend.feign;

import by.bntu.fitr.frontend.dto.feign.AccountCreateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @PostMapping(value = "/api/v1/accounts/")
    AccountCreateDto saveAccount(@RequestBody AccountCreateDto accountCreateDto);

    @PostMapping(value = "/api/v1/accounts/login")
    String login(@RequestBody Map<String, String> params);
}
