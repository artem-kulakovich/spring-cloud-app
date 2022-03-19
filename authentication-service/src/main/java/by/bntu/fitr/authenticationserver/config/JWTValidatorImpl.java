package by.bntu.fitr.authenticationserver.config;

import by.bntu.fitr.authenticationserver.constant.JWTConstant;
import by.bntu.fitr.authenticationserver.utils.HmacSHA256Custom;
import by.bntu.fitr.authenticationserver.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

public class JWTValidatorImpl implements JWTValidator {
    @Autowired
    private HmacSHA256Custom hmacSHA256Custom;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Map<String, String> parseToken(String token) {
        String[] parts = token.split(".");
        Map<String, String> tokenParts = new HashMap<>();
        tokenParts.put(JWTConstant.JWT_HEADER, parts[0]);
        tokenParts.put(JWTConstant.JWT_PAYLOAD, parts[1]);
        tokenParts.put(JWTConstant.JWT_SIGNATURE, parts[2]);
        return tokenParts;
    }

    @Override
    public boolean validateToken(Map<String, String> tokenParts, String token) {
        String header = jwtUtil.decodeHeader(tokenParts.get("header"));
        String payload = jwtUtil.decodePayload(tokenParts.get("payload"));

        return false;
    }


}
