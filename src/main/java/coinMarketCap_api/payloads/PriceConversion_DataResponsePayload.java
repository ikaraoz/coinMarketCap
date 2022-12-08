package coinMarketCap_api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class PriceConversion_DataResponsePayload {
    //"data": {
//        "id": 3541,
//                "symbol": "GTQ",
//                "name": "Guatemalan Quetzal",
//                "amount": 10000000,
//                "last_updated": "2022-12-06T23:06:23.000Z",
//                "quote": {
//            "2791": {
//                "price": 1045875.0861061916,
//                        "last_updated": "2022-12-06T23:06:23.000Z"
//            }
//        }
//    }
    @JsonProperty("id")
    private int id;
    @JsonProperty("symbol")
    private String symbol;


    @JsonProperty("name")
    private String name;
    @JsonProperty("amount")
    private int amount;

    @JsonProperty("last_updated")
    private String last_updated;

    @JsonProperty("quote")
    private JsonNode quote;

    public int getAmount() {
        return amount;
    }

    private JsonNode getQuote() {
        return quote;
    }

    public String getName() {
        return name;
    }

    public String getPrice(String currencySymbol) {
        return getQuote().get(currencySymbol).get("price").asText();
    }
}
