package by.bntu.fitr.authenticationserver.controller;


import by.bntu.fitr.authenticationserver.config.JWTConfig;
import by.bntu.fitr.authenticationserver.constant.JWTConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/jwt")
public class JWTController {
    private final JWTConfig jwtConfig;

    @Autowired
    public JWTController(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendJwtToken(@RequestBody Map<String, String> params) {
        String jwtToken = jwtConfig.getJwtProvider().createToken(params.get(JWTConstant.JWT_HEADER),
                params.get(JWTConstant.JWT_PAYLOAD));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authenticated", jwtToken);
        return new ResponseEntity<>(jwtToken, httpHeaders, HttpStatus.OK);
    }
}
