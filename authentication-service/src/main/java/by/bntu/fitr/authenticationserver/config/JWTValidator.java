package by.bntu.fitr.authenticationserver.config;

import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;

import java.util.Map;

public interface JWTValidator {

    JWTResponseDTO parseToken(String token);

    boolean validateToken(JWTResponseDTO jwtResponseDTO, String token);
}
