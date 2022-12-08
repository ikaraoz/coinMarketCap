package coinMarketCap_ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private int rowFiltervalue;
    JavascriptExecutor js;

    @FindBy(css = ".sc-c7b56da4-0.BcsEj.popped")
    private WebElement popUpDismissButton;
    @FindBy(css = ".sc-aef7b723-0.sc-dae82938-0.coScOT")
    private WebElement rowFilterButton;
    @FindBy(css = ".sc-aef7b723-0.sc-f84d4cca-0.ezlhyG.dropdown-container")
    private WebElement rowFilterDropDownContainer;
    @FindBy(css = ".sc-aef7b723-0.jhvPQd")
    private WebElement searchPopUp;

    @FindBy(xpath = "//table[@class='sc-f7a61dda-3 kCSmOD cmc-table  ']//thead//tr//th//descendant::p")
    private List<WebElement> tableAllHeadersElement;

    @FindBy(xpath = "//table[@class='sc-f7a61dda-3 kCSmOD cmc-table  ']//tbody//tr")
    private List<WebElement> tableAllRowsElements;
    @FindBy(xpath = "(//*[text()='Filters'])[2]")
    private WebElement FiltersButton;
    @FindBy(xpath = "//*[contains(text(),'Algorithm')]")
    private WebElement AlgorithmButton;
    @FindBy(css = ".sc-ecd5a54c-1.hCwYVK.cmc-input")
    private WebElement AlgorithmSearchButton;
    @FindBy(xpath = "//*[text()='Add Filter']")
    private WebElement addFilters;

    @FindBy(xpath = "//label[@id='mineable']//span")
    private WebElement mineableToggle;

    @FindBy(xpath = "//*[text()='All Cryptocurrencies']")
    private WebElement allCryptocurrenciesMenu;
    @FindBy(xpath = "//button[text()='Price']")
    private WebElement priceMenu;

    @FindBy(xpath = "//*[@data-qa-id='range-filter-input-min']")
    private WebElement minValue;
    @FindBy(xpath = "//*[@data-qa-id='range-filter-input-max']")
    private WebElement maxValue;

    @FindBy(xpath = "//*[@data-qa-id='filter-dd-button-apply']")
    private WebElement applyFilterButton;

    @FindBy(css = ".sc-a4a6801b-0.cXksaI.cmc-filter-button")
    private WebElement showResultsButton;

    @FindBy(xpath = "//*[text()='Maybe later']")
    private WebElement maybeLaterButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAlgorithmFilters(String filter) throws InterruptedException {

        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        wait.until(ExpectedConditions.visibilityOf(FiltersButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(AlgorithmButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(AlgorithmSearchButton)).sendKeys(filter);
        driver.findElement(By.xpath("//*[text()='" + filter + "']")).click();
    }

    public void addFilters() {
        addFilters.click();
    }

    public void enableMineable() {
        wait.until(ExpectedConditions.elementToBeClickable(mineableToggle)).click();
    }

    public void filterAllCryptocurrencies() {
        wait.until(ExpectedConditions.elementToBeClickable(allCryptocurrenciesMenu)).click();
    }

    public void selectCryptoCurrencies(String filter) {
        WebElement f = driver.findElement((By.xpath("//*[text()='" + filter + "']")));
        while(!f.isDisplayed()){
            filterAllCryptocurrencies();
        }
        f.click();
    }

    public void filterByPrice(int minValue, int maxValue) {
        wait.until(ExpectedConditions.elementToBeClickable(priceMenu)).click();
        this.minValue.sendKeys(Integer.toString(minValue));
        this.maxValue.sendKeys(Integer.toString(maxValue));

    }

    public void applyFilter() {

        wait.until(ExpectedConditions.elementToBeClickable(applyFilterButton)).click();
//        wait.until(ExpectedConditions.elementToBeClickable(popUpDismissButton)).click();
        showResultsButton.click();
    }

    public void setRowFilter(int rowFilterValue) throws InterruptedException {
        this.rowFiltervalue = rowFilterValue;
        wait.until(ExpectedConditions.elementToBeClickable(searchPopUp)).click();
        wait.until((
                        ExpectedConditions.elementToBeClickable(rowFilterButton)))
                .click();
        wait.until((
                        ExpectedConditions.elementToBeClickable(rowFilterDropDownContainer)))
                .findElement(By.xpath("//*[@class='sc-a4a6801b-0 jgNqHP' and text()='" + rowFilterValue + "']"))
                .click();
    }

    public int getTable(String informationToBeCapturedFromTable) {

        List<String> eachInformationToBeCapturedFromTable = Arrays.asList(informationToBeCapturedFromTable.split(",", -1));
        wait.until(ExpectedConditions.textToBePresentInElement(rowFilterButton, Integer.toString(rowFiltervalue)));

        List<WebElement> tableAllHeadersElement = this.tableAllHeadersElement;
        List<String> allHeaderNames = new ArrayList<String>();
        for (WebElement header : tableAllHeadersElement) {
            String headerName = header.getText();
            allHeaderNames.add(headerName);
        }

        List<LinkedHashMap<String, String>> allTableData = new ArrayList<LinkedHashMap<String, String>>();

        List<WebElement> tableAllRowsElements = this.tableAllRowsElements;
        for (int i = 1; i <= tableAllRowsElements.size(); i++) {
            WebElement specificRowLocation = tableAllRowsElements.get(i - 1);
            js.executeScript("arguments[0].scrollIntoView()", specificRowLocation);
            List<WebElement> allColumnsElements = specificRowLocation
                    .findElements(By.tagName("td"));
            LinkedHashMap<String, String> eachRowData = new LinkedHashMap<>();
            for (int j = 1; j < allColumnsElements.size() - 1; j++) {
                String cellValue = allColumnsElements.get(j).getText();
                String headerName = allHeaderNames.get(j - 1);
                for (String info : eachInformationToBeCapturedFromTable) {
                    if (info.equalsIgnoreCase(headerName) || headerName.equals("#")) {
                        eachRowData.put(headerName, cellValue);
                    }
                }
            }
            allTableData.add(eachRowData);
        }
        System.out.println("allTableData = " + allTableData);
        System.out.println("allTableData.size(); = " + allTableData.size());
        return allTableData.size();

    }
}
