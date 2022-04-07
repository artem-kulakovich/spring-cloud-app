package by.bntu.fitr.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailDTO {
    @JsonProperty(value = "to")
    private String to;

    @JsonProperty(value = "subject")
    private String subject;

    @JsonProperty(value = "msg")
    private String msg;
}
