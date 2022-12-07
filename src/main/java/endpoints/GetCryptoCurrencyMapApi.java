package endpoints;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetCryptoCurrencyMapApi extends BaseApi {
    private static final String getCryptoCurrencyIdsUrl = baseUrl + "/cryptocurrency/map";

    public static Response getCryptoCurrencyId(String CryptoCurrencyName){

        Response resp= given()
                .spec(AuthSetup())
                .when()
                .get(getCryptoCurrencyIdsUrl);
        return resp;
    }
}
