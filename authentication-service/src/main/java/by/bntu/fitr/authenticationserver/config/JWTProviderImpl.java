package by.bntu.fitr.authenticationserver.config;

import by.bntu.fitr.authenticationserver.utils.HmacSHA256Custom;
import by.bntu.fitr.authenticationserver.utils.JWTUtil;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;

@NoArgsConstructor
public class JWTProviderImpl implements JWTProvider {

    @Autowired
    private HmacSHA256Custom hmacSHA256Custom;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public String createToken(String header, String payload) {
        String unSignedToken = jwtUtil.encodeHeader(header) + "." + jwtUtil.encodePayload(payload);
        String signature = hmacSHA256Custom.encode(unSignedToken);
        String jwtToken = unSignedToken + "." + jwtUtil.encodeSignature(signature);
        return jwtToken;
    }


}
