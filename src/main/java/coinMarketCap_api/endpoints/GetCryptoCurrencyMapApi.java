package coinMarketCap_api.endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetCryptoCurrencyMapApi extends BaseApi {
    private static final String getCryptoCurrencyIdsUrl = getBaseUrl() + "/cryptocurrency/map";

    public static Response getCryptoCurrencyIds(){

        Response resp= given()
                .spec(AuthSetup())
                .when()
                .get(getCryptoCurrencyIdsUrl);
        return resp;
    }
}
