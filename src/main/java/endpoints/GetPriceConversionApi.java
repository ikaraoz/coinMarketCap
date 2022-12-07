package endpoints;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public class GetPriceConversionApi extends BaseApi {
    private static final String getPriceConversionUrl = baseUrl + "/tools/price-conversion";

    public static Response getPriceConversion(String baseCurrencySymbol, String targetCurrencySymbols, double amountOfCurrencyToConvert) {
        Response resp =
                given()
                        .spec(AuthSetup())
                        .queryParams("symbol", baseCurrencySymbol, "convert", targetCurrencySymbols, "amount", amountOfCurrencyToConvert)
                        .when()
                        .get(getPriceConversionUrl);
        resp.then().log().all();
        return resp;
    }
}
