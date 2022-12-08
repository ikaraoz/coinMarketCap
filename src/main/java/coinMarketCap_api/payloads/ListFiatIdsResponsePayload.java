package coinMarketCap_api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListFiatIdsResponsePayload {

    @JsonProperty("data")
    private List<FiatIds_DataResponsePayload> fiatData;
    @JsonProperty("status")
    private BaseStatusResponsePayload status;

    public List<FiatIds_DataResponsePayload> getFiatData() {
        return fiatData;
    }

    public BaseStatusResponsePayload getFiatStatus() {
        return status;
    }
}
