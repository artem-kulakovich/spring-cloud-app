package by.bntu.fitr.accountservice.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JSONPayload {
    @JsonProperty(value = "userName")
    private String userName;

    @JsonProperty(value = "roleNames")
    private List<String> roleName;

}
