package stepDefinitions;

import coinMarketCap_api.payloads.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;

import static coinMarketCap_api.endpoints.GetCryptoCurrencyMapApi.getCryptoCurrencyId;
import static coinMarketCap_api.endpoints.GetFiatCurrencyMapApi.getFiatCurrencyId;
import static coinMarketCap_api.endpoints.GetPriceConversionApi.getPriceConversion;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class apiStepDefs {
    Response response;
    double amount;
    String targetCryptoCurrencySymbol;
    String targetFiatSymbol;
    String sourceFiatSymbol;
    double fiatToFiatConversionPrice;
    double fiatToCryptoConversionPrice;

    @Given("the user has {long} {string}")
    public void theUserHas(long amount, String sourceFiatName) {
        this.amount = amount;
    }

    @And("the exchanges fiat {string} with fiat {string}")
    public void theExchangesFiatWithFiat(String targetFiatName, String sourceFiatName) {
        targetFiatSymbol = getFiatSymbol(targetFiatName);
        sourceFiatSymbol = getFiatSymbol(sourceFiatName);
        fiatToFiatConversionPrice =
                Double.parseDouble(getPriceConversion(sourceFiatSymbol, targetFiatSymbol, amount)
                        .as(PriceConversionResponsePayload.class)
                        .getData()
                        .getPrice(targetFiatSymbol));
        System.out.println("fiatToFiatConversionPrice = " + fiatToFiatConversionPrice);
    }

    @And("the user buys crypto {string} with fiat {string}")
    public void theUserBuysCryptoWithFiat(String targetCryptoCurrencyName, String targetFiatName) {
        targetCryptoCurrencySymbol = getCryptoSymbol(targetCryptoCurrencyName);
        targetFiatSymbol = getFiatSymbol(targetFiatName);
        response = getPriceConversion(targetFiatSymbol, targetCryptoCurrencySymbol, fiatToFiatConversionPrice);
        fiatToCryptoConversionPrice =
                Double.parseDouble(response
                .as(PriceConversionResponsePayload.class)
                .getData()
                .getPrice(targetCryptoCurrencySymbol));
        System.out.println("fiatToCryptoConversionPrice = " + fiatToCryptoConversionPrice);

    }

    @And("the error_code should be {int}")
    public void theError_codeShouldBe(int errorCode) {
        int actualErrorCode = response.as(PriceConversionResponsePayload.class).getStatus().getError_code();
        Assert.assertEquals(errorCode, actualErrorCode);
    }

    @And("data.name should match {string}")
    public void dataNameShouldMatch(String targetFiatName) {
        String actualDataName = response.as(PriceConversionResponsePayload.class).getData().getName();
        Assert.assertEquals(targetFiatName, actualDataName);
    }

    @And("{string} price should not be null")
    public void priceShouldNotBeNull(String targetCryptoCurrencyName) {
        assertThat(response.as(PriceConversionResponsePayload.class).getData().getPrice(targetCryptoCurrencySymbol),is(notNullValue()));
    }


    public String getCryptoSymbol(String currencyName) {
        Response resp = getCryptoCurrencyId(currencyName);
        String symbol = "temp";
        try {
            List<CryptoIds_DataResponsePayload> data = resp.as(ListCryptoIdsResponsePayload.class).getCryptoData();
            for (CryptoIds_DataResponsePayload d : data) {
                if (d.getCryptoName().equalsIgnoreCase(currencyName)) {
                    symbol = d.getCryptoSymbol();
                    return symbol;
                }
            }
        } catch (Exception e) {
            System.out.println("No such crypto currency found!");
        }
        return symbol;
    }

    public String getFiatSymbol(String currencyName) {
        Response resp = getFiatCurrencyId(currencyName);
        String symbol = "temp";
        try {
            List<FiatIds_DataResponsePayload> data = resp.as(ListFiatIdsResponsePayload.class).getFiatData();
            for (FiatIds_DataResponsePayload d : data) {
                if (d.getFiatName().equalsIgnoreCase(currencyName)) {
                    symbol = d.getFiatSymbol();
                    return symbol;
                }
            }
        } catch (Exception e) {
            System.out.println("No such fiat currency found!");
        }
        return symbol;
    }
}
