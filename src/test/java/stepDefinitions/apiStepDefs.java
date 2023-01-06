package stepDefinitions;

import coinMarketCap_api.payloads.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigurationReader;

import java.util.List;

import static coinMarketCap_api.endpoints.GetCryptoCurrencyMapApi.getCryptoCurrencyIds;
import static coinMarketCap_api.endpoints.GetFiatCurrencyMapApi.getFiatCurrencyIds;
import static coinMarketCap_api.endpoints.GetPriceConversionApi.getPriceConversion;
import static coinMarketCap_api.endpoints.BaseApi.*;
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

    @Before("not @uiTests")
    public void setEnv() {
        String apiKey = null;
        String baseUrl = null;
        String environment = System.getProperty("environment") == null ? environment = "production" : System.getProperty("environment");
        switch (environment) {
            case "production":
                apiKey = ConfigurationReader.get("prod_apiKey");
                baseUrl = ConfigurationReader.get("prod_coinMarketCapApi_baseUrl");
                break;
            case "sandbox":
                apiKey = ConfigurationReader.get("sandbox_apiKey");
                baseUrl = ConfigurationReader.get("sandbox_coinMarketCapApi_baseUrl");
                break;
        }
        setBaseUrl(baseUrl);
        setApiKey(apiKey);
    }

    @Given("the user has {long} {string}")
    public void theUserHas(long amount, String sourceFiatName) {
        this.amount = amount;
    }

    @And("the user exchanges fiat {string} with fiat {string}")
    public void theUserExchangesFiatWithFiat(String targetFiatName, String sourceFiatName) {
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

    @Then("the error_code should be {int}")
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
        assertThat(response.as(PriceConversionResponsePayload.class).getData().getPrice(targetCryptoCurrencySymbol), is(notNullValue()));
    }


    public String getCryptoSymbol(String currencyName) {
        Response resp = getCryptoCurrencyIds();
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
        Response resp = getFiatCurrencyIds();
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
