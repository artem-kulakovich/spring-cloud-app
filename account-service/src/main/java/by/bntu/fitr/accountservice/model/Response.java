package by.bntu.fitr.accountservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Response {
    private String reason;
    private String msg;

    public Response(String reason, String msg) {
        this.reason = reason;
        this.msg = msg;
    }
}
