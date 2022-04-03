package by.bntu.fitr.authenticationserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@ApiModel(description = "jwt request dto")
@Getter
@Setter
public class JWTRequestDTO {
    @ApiModelProperty(value = "jwtHeader", required = true, position = 0)
    @JsonProperty(value = "header")
    private Map<String, String> header;

    @ApiModelProperty(value = "jwtPayload", required = true, position = 1)
    @JsonProperty(value = "payload")
    private Map<String, String> payload;

    public JWTRequestDTO(Map<String, String> header, Map<String, String> payload) {
        this.header = header;
        this.payload = payload;
    }
}