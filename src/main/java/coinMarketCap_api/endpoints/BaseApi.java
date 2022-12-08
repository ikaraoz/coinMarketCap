package coinMarketCap_api.endpoints;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class BaseApi {

    private static String baseUrl;

    private static String apiKey;
    private static RequestSpecification requestSpec;

    public static RequestSpecification AuthSetup() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Accept", "application/json")
                .addHeader("X-CMC_PRO_API_KEY", apiKey);

        requestSpec = builder.build();

        return requestSpec;

    }
    public static void setBaseUrl(String bUrl) {
        baseUrl = bUrl;
    }

    public static void setApiKey(String aKey) {
        apiKey = aKey;
    }
    public static String getBaseUrl() {
        return baseUrl;
    }

}
