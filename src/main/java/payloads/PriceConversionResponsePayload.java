package payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceConversionResponsePayload {


    @JsonProperty("status")
    private BaseStatusResponsePayload status;
    @JsonProperty("data")
    private PriceConversion_DataResponsePayload data;

    public BaseStatusResponsePayload getStatus() {
        return status;
    }

    public PriceConversion_DataResponsePayload getData() {
        return data;
    }

}
