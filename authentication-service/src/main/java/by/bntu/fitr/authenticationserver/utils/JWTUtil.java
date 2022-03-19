package by.bntu.fitr.authenticationserver.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class JWTUtil {

    public String encodeHeader(String header) {
        return Base64.getEncoder().encodeToString(header.getBytes());
    }

    public String encodePayload(String payload) {
        return Base64.getEncoder().encodeToString(payload.getBytes());
    }

    public String encodeSignature(String signature) {
        return Base64.getEncoder().encodeToString(signature.getBytes());
    }

    public String decodeHeader(String header) {
        return new String(Base64.getDecoder().decode(header.getBytes()));
    }

    public String decodePayload(String payload) {
        return new String(Base64.getDecoder().decode(payload.getBytes()));
    }

    public String decodeSignature(String signature) {
        return new String(Base64.getDecoder().decode(signature.getBytes()));
    }
}
