# Api and UI tests - coinMarketCap
This project contains API and UI tests for coinMarketCap.

## Project Structure

Feature files are located in path```src/test/resources```

Step definitions were implemented in ```src/test/java/stepDefinitions```

API test endpoints and response payloads are located at package ```src/main/java/coinMarketCap_api```

UI POJO classes are implemented at package ```src/main/java/coinMarketCap_ui```

## Running tests

```
$ ./gradlew runCucmberTests
```
## Test reports
Once test run has finished, test report file is generated into the location ```target/cucumber-report.html```.

Test generates Cucumber Report url link into the command line output ```https://reports.cucumber.io.reports/{{reportId}}```. This url can be clicked and report can be viewed.

## Switching environments
```sandbox``` and ```production``` environments were defined, but command line implementation is in progress.

## Switching browsers
Switching between ```chrome``` and ```firefox``` environments were defined, but command line implementation is in progress. 