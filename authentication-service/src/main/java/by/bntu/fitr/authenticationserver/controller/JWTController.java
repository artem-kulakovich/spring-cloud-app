package by.bntu.fitr.authenticationserver.controller;


import by.bntu.fitr.authenticationserver.config.JWTConfig;
import by.bntu.fitr.authenticationserver.dto.JWTRequestDTO;
import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/jwt")
public class JWTController {
    private final JWTConfig jwtConfig;

    @Autowired
    public JWTController(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendJwtToken(@RequestBody JWTRequestDTO jwtRequestDTO) {
        JWTResponseDTO jwtResponseDTO = jwtConfig.getJwtProvider().createToken(jwtRequestDTO);
        return new ResponseEntity<>(jwtResponseDTO.getHeader() + "." + jwtResponseDTO.getPayload() + "."
                + jwtResponseDTO.getSignature(), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> validateJwtToken(@RequestParam String token) {
        JWTResponseDTO jwtResponseDTO = jwtConfig.getJwtValidator().parseToken(token);
        boolean isValid = jwtConfig.getJwtValidator().validateToken(jwtResponseDTO, token);
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }
}
