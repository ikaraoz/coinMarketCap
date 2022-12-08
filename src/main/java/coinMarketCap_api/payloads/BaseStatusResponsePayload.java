package coinMarketCap_api.payloads;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;


public class BaseStatusResponsePayload {

//"status": {
//        "timestamp": "2022-12-06T10:01:38.900Z",
//                "error_code": 0,
//                "error_message": null,
//                "elapsed": 30,
//                "credit_count": 1,
//                "notice": null
//    }

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("error_code")
    private int error_code;
    @JsonProperty("error_message")
    private JsonNode error_message;

    @JsonProperty("elapsed")
    private String elapsed;
    @JsonProperty("credit_count")
    private String credit_count;

    @JsonProperty("notice")
    private JsonNode notice;

    public int getError_code() {
        return error_code;
    }
    public JsonNode getError_message() {
        return error_message;
    }
}
