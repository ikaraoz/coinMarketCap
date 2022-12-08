package coinMarketCap_api.endpoints;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;


public class BaseApi {
    public static final String baseUrl = "https://pro-api.coinmarketcap.com/v1";
    private static RequestSpecification requestSpec;

    @BeforeClass
    public static RequestSpecification AuthSetup() {

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Accept", "application/json")
                .addHeader("X-CMC_PRO_API_KEY", "d140bdbb-776d-4189-a9df-03a51312d66a");

        requestSpec = builder.build();
        return requestSpec;

    }

}
