package by.bntu.fitr.frontend.wrapper;

import by.bntu.fitr.frontend.dto.feign.AccountCreateDto;
import by.bntu.fitr.frontend.feign.AccountServiceClient;
import by.bntu.fitr.frontend.model.FeignResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountServiceClientWrapper {
    private final AccountServiceClient accountServiceClient;

    @Autowired
    public AccountServiceClientWrapper(AccountServiceClient accountServiceClient) {
        this.accountServiceClient = accountServiceClient;
    }

    public AccountCreateDto saveAccount(AccountCreateDto accountCreateDto) {
        try {
            return accountServiceClient.saveAccount(accountCreateDto);
        } catch (FeignException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void login(Map<String, String> params) {
        try {
            accountServiceClient.login(params);
        } catch (FeignException e) {
            System.out.println(e.getMessage());
            FeignResponse feignResponse = new FeignResponse(e.getMessage());
            System.out.println(feignResponse.getMethod());
            System.out.println(feignResponse.getStatusCode());
            System.out.println(feignResponse.getUri());
            System.out.println(feignResponse.getResponse());

        }
    }

}
