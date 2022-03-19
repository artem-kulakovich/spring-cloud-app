package by.bntu.fitr.authenticationserver.config;

import java.util.Map;

public interface JWTValidator {

    Map<String, String> parseToken(String token);

    boolean validateToken(Map<String, String> tokenParts, String token);
}
