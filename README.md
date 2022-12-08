# Api and UI tests - coinMarketCap
This project contains API and UI tests for coinMarketCap.

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