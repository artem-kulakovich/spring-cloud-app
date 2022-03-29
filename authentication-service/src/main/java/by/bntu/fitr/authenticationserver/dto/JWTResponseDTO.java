package by.bntu.fitr.authenticationserver.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class JWTResponseDTO {
    @JsonProperty(value = "header")
    private String header;

    @JsonProperty(value = "payload")
    private String payload;

    @JsonProperty(value = "signature")
    private String signature;

    public JWTResponseDTO(String header, String payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }
}
