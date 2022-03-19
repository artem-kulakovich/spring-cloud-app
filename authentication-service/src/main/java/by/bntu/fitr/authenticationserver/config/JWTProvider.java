package by.bntu.fitr.authenticationserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;


public interface JWTProvider {
    String createToken(String header, String payload);
}
