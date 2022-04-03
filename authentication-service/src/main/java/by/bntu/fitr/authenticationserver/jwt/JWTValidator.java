package by.bntu.fitr.authenticationserver.jwt;

import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;


public interface JWTValidator {

    JWTResponseDTO parseToken(String token);

    boolean validateToken(JWTResponseDTO jwtResponseDTO, String token);
}
