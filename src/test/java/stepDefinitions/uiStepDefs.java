package stepDefinitions;

import coinMarketCap_ui.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class uiStepDefs {
    WebDriver driver;
    HomePage homePage;
    public List<String> eachInformationToBeCapturedFromTable;

    @Before
    public void setUp() throws InterruptedException {
        this.driver = Driver.getDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("the user visits {string}")
    public void theUserVisits(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @And("filters by Algorithm - {string}")
    public void filtersByAlgorithm(String filter) throws InterruptedException {
        homePage.selectAlgorithmFilters(filter);
    }

    @And("adds filters")
    public void addsFilters() {
        homePage.addFilters();
    }

    @And("toggles {string}")
    public void toggles(String arg0) throws InterruptedException {
        homePage.enableMineable();
    }

    @And("selects {string}")
    public void selects(String filter) {
        homePage.filterAllCryptocurrencies(filter);
    }

    @And("filters rows by {int}")
    public void filtersRowsBy(int rowFilterValue) throws InterruptedException {
        homePage.setRowFilter(rowFilterValue);
    }

    @Then("the number of rows should not be more than {int}")
    public void theNumberOfRowsShouldNotBeMoreThan(int filterValue) {
        int numberOfCurrency = homePage.getTable("#");
        Assert.assertTrue(numberOfCurrency < filterValue);
    }

    @And("captures page contents with information {string}")
    public void capturesPageContentsWithInformation(String informationToBeCapturedFromTable) {
        homePage.getTable(informationToBeCapturedFromTable);

    }

    @And("sets minimum value to {int} and maximum value to {int}")
    public void setsMinimumValueToAndMaximumValueTo(int minValue, int maxValue) throws InterruptedException {
        homePage.filterByPrice(minValue, maxValue);
        homePage.applyFilter();
    }
}
