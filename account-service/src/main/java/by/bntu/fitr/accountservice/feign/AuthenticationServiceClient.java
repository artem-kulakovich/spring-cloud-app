package by.bntu.fitr.accountservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "authentication-service")
public interface AuthenticationServiceClient {

    @PostMapping(value = "/api/v1/jwt")
    String createJwt(@RequestBody Map<String, String> params);
}

