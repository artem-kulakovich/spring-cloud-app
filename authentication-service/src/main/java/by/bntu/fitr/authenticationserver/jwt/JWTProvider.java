package by.bntu.fitr.authenticationserver.jwt;

import by.bntu.fitr.authenticationserver.dto.JWTRequestDTO;
import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;


public interface JWTProvider {

    JWTResponseDTO createToken(JWTRequestDTO jwtRequestDTO);
}
