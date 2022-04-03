package by.bntu.fitr.authenticationserver.jwt;

import by.bntu.fitr.authenticationserver.dto.JWTRequestDTO;
import by.bntu.fitr.authenticationserver.exception.CantCreateJWTTokenException;
import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;
import by.bntu.fitr.authenticationserver.utils.HmacSHA256Custom;
import by.bntu.fitr.authenticationserver.utils.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class JWTProviderImpl implements JWTProvider {

    @Autowired
    private HmacSHA256Custom hmacSHA256Custom;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public JWTResponseDTO createToken(JWTRequestDTO jwtRequestDTO) {
        try {
            JWTResponseDTO jwtResponseDTO = new JWTResponseDTO();
            jwtResponseDTO.setHeader(jwtUtil.encodeHeader(new ObjectMapper().writeValueAsString(jwtRequestDTO.getHeader())));
            jwtResponseDTO.setPayload(jwtUtil.encodePayload(new ObjectMapper().writeValueAsString(jwtRequestDTO.getPayload())));
            String unSignedToken = jwtResponseDTO.getHeader() + "." + jwtResponseDTO.getPayload();
            jwtResponseDTO.setSignature(jwtUtil.encodeSignature(hmacSHA256Custom.encode(unSignedToken)));
            return jwtResponseDTO;
        } catch (JsonProcessingException e) {
            throw new CantCreateJWTTokenException("cant create jwt exception");
        }
    }


}
