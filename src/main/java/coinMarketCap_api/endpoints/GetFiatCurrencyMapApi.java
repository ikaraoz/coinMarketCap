package coinMarketCap_api.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetFiatCurrencyMapApi extends BaseApi {
    private static final String getFiatCurrencyIdsUrl = getBaseUrl() + "/fiat/map";

    public static Response getFiatCurrencyId(String fiatCurrencyName) {
        Response resp = given()
                .spec(AuthSetup())
                .when()
                .get(getFiatCurrencyIdsUrl);
        return resp;
    }

}
