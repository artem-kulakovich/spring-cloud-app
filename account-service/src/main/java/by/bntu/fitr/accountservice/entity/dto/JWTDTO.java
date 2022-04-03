package by.bntu.fitr.accountservice.entity.dto;

import by.bntu.fitr.accountservice.constant.JWTConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class JWTDTO {
    @JsonProperty(value = "header")
    private Map<String, String> header;
    @JsonProperty(value = "payload")
    private Map<String, String> payload;

    public JWTDTO(Map<String, String> header, Map<String, String> payload) {
        this.header = header;
        this.payload = payload;
    }

    public JWTDTO() {
        initParams();
    }

    public void setParams(String alg, String type, String userName, String roles) {
        header.put(JWTConstant.JWT_ALG, alg);
        header.put(JWTConstant.JWT_TYPE, type);
        payload.put(JWTConstant.JWT_USER_NAME, userName);
        payload.put(JWTConstant.JWT_ROLES, roles);
    }

    private void initParams() {
        header = new HashMap<>();
        payload = new HashMap<>();
    }
}
