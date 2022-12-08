package coinMarketCap_api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FiatIds_DataResponsePayload {


//     "id": 2781,
//             "name": "United States Dollar",
//             "sign": "$",
//             "symbol": "USD"

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("symbol")
    private String symbol;


    @JsonProperty("sign")
    private String sign;

    public int getFiatId() {
        return id;
    }

    public String getFiatSign() {
        return sign;
    }

    public String getFiatName() {
        return name;
    }

    public String getFiatSymbol() {
        return symbol;
    }

}
