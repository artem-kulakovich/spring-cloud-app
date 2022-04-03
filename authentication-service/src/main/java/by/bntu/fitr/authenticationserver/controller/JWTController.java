package by.bntu.fitr.authenticationserver.controller;

import by.bntu.fitr.authenticationserver.jwt.JWTConfig;
import by.bntu.fitr.authenticationserver.dto.JWTRequestDTO;
import by.bntu.fitr.authenticationserver.dto.JWTResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "authentication-service", description = "this service provides operations to create and validate jwt")
@RestController
@RequestMapping(value = "/api/v1/jwt")
public class JWTController {
    private final JWTConfig jwtConfig;

    @Autowired
    public JWTController(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @ApiOperation(value = "create jwt", response = JWTResponseDTO.class)
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendJwtToken(@RequestBody JWTRequestDTO jwtRequestDTO) {
        JWTResponseDTO jwtResponseDTO = jwtConfig.getJwtProvider().createToken(jwtRequestDTO);
        return new ResponseEntity<>(jwtResponseDTO.getHeader() + "." + jwtResponseDTO.getPayload() + "."
                + jwtResponseDTO.getSignature(), HttpStatus.OK);
    }

    @ApiOperation(value = "validate jwt", response = Boolean.class)
    @GetMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> validateJwtToken(@RequestParam String token) {
        JWTResponseDTO jwtResponseDTO = jwtConfig.getJwtValidator().parseToken(token);
        boolean isValid = jwtConfig.getJwtValidator().validateToken(jwtResponseDTO, token);
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }
}
