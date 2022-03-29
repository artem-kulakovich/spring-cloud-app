package by.bntu.fitr.authenticationserver.config;

import by.bntu.fitr.authenticationserver.dto.JWTRequestDTO;
import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;


public interface JWTProvider {
    JWTResponseDTO createToken(JWTRequestDTO jwtRequestDTO);
}
