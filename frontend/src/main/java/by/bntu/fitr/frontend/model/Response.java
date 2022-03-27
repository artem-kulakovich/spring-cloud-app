package by.bntu.fitr.frontend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @JsonProperty(value = "reason")
    private String reason;

    @JsonProperty(value = "msg")
    private String msg;
}
