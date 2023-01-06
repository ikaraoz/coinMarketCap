package coinMarketCap_api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class CryptoIds_DataResponsePayload {
//"id": 1,
//        "name": "Bitcoin",
//        "symbol": "BTC",
//        "slug": "bitcoin",
//        "rank": 1,
//        "displayTV": 1,
//        "manualSetTV": 0,
//        "tvCoinSymbol": "",
//        "is_active": 1,
//        "first_historical_data": "2013-04-28T18:47:21.000Z",
//        "last_historical_data": "2023-01-05T22:59:00.000Z",
//        "platform": null

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("rank")
    private int rank;
    @JsonProperty("displayTV")
    private int displayTV;
    @JsonProperty("manualSetTV")
    private int manualSetTV;
    @JsonProperty("tvCoinSymbol")
    private String tvCoinSymbol;
    @JsonProperty("is_active")
    private int is_active;
    @JsonProperty("first_historical_data")
    private String first_historical_data;
    @JsonProperty("last_historical_data")
    private String last_historical_data;
    @JsonProperty("platform")
    private JsonNode platform;

    public int getCryptoId() {
        return id;
    }

    public String getCryptoName() {
        return name;
    }

    public String getCryptoSymbol() {
        return symbol;
    }
}
