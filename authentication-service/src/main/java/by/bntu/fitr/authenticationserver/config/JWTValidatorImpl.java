package by.bntu.fitr.authenticationserver.config;

import by.bntu.fitr.authenticationserver.constant.JWTConstant;
import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;
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
