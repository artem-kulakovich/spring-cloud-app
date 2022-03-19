package by.bntu.fitr.accountservice.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JSONHeader {
    @JsonProperty(value = "alg")
    private String alg;

    @JsonProperty(value = "type")
    private String type;
}
