package payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListCryptoIdsResponsePayload {

    @JsonProperty("data")
    private List<CryptoIds_DataResponsePayload> cryptoData;

    @JsonProperty("status")
    private BaseStatusResponsePayload status;


    public List<CryptoIds_DataResponsePayload> getCryptoData() {
        return cryptoData;
    }

    public BaseStatusResponsePayload getCryptoStatus() {
        return status;
    }
}
