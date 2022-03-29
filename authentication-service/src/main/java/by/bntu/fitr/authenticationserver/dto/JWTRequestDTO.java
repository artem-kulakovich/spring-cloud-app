package by.bntu.fitr.authenticationserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class JWTRequestDTO {
    @JsonProperty(value = "header")
    private Map<String, String> header;

    @JsonProperty(value = "payload")
    private Map<String, String> payload;

    public JWTRequestDTO(Map<String, String> header, Map<String, String> payload) {
        this.header = header;
        this.payload = payload;
    }
}