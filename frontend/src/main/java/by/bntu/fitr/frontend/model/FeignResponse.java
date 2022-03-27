package by.bntu.fitr.frontend.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FeignResponse {
    @Setter(value = AccessLevel.PRIVATE)
    @Getter(value = AccessLevel.PRIVATE)
    private String[] payload;

    private int statusCode;

    private String method;

    private String uri;

    private Response[] response;

    public FeignResponse(String errorStr) {
        load(errorStr);
    }

    public void load(String errorStr) {
        payload = errorStr.split(" ");
        setParams();
    }

    public void setParams() {
        statusCode = Integer.parseInt(payload[0].replaceAll("[\\[\\]]", ""));
        method = payload[2].replaceAll("[\\[\\]]", "");
        uri = payload[4].replaceAll("[\\[\\]]", "");
        try {
            for (int i = 0; i < payload.length; i++) {
                System.out.println(payload[i]);
            }
            response = new ObjectMapper().readValue(payload[6], Response[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
