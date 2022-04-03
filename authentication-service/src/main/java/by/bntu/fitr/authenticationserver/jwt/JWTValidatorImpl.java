package by.bntu.fitr.authenticationserver.jwt;

import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;
import by.bntu.fitr.authenticationserver.utils.HmacSHA256Custom;
import by.bntu.fitr.authenticationserver.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;


public class JWTValidatorImpl implements JWTValidator {
    @Autowired
    private HmacSHA256Custom hmacSHA256Custom;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public JWTResponseDTO parseToken(String token) {
        String[] parts = token.split(".");
        JWTResponseDTO jwtResponseDTO = new JWTResponseDTO();
        jwtResponseDTO.setHeader(parts[0]);
        jwtResponseDTO.setPayload(parts[1]);
        jwtResponseDTO.setSignature(parts[2]);
        return jwtResponseDTO;
    }

    @Override
    public boolean validateToken(JWTResponseDTO jwtResponseDTO, String token) {
        String unSignedToken = jwtResponseDTO.getHeader() + "." + jwtResponseDTO.getPayload();
        String assignedToken = hmacSHA256Custom.encode(unSignedToken);
        if (assignedToken.equals(jwtResponseDTO.getSignature())) {
            return true;
        }
        return false;
    }


}
