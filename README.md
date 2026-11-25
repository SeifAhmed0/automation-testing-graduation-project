# OrangeHRM Automation Testing (Graduation Project)

Automated UI tests for the [OrangeHRM Open Source demo](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login), built with **Selenium WebDriver**, **TestNG** and the **Page Object Model (POM)**, with **Allure** reporting.

## Tech stack

| Area | Tool |
|---|---|
| Language | Java 21 |
| Build | Maven |
| UI automation | Selenium WebDriver 4.52.0 |
| Test framework | TestNG 7.12.0 |
| Test data | JSON, read with Jackson |
| Reporting | Allure (with the AspectJ agent for `@Step`) |

## Features

- **Page Object Model:** locators and actions live in page classes, assertions live in tests.
- **Explicit waits only:** no `Thread.sleep()`.
- **Data-driven:** test data is stored in a JSON file instead of being hardcoded in the tests.
- **Parallel execution:** `testng.xml` uses `parallel="tests"` with `thread-count="3"`, and the driver is a `ThreadLocal<WebDriver>`, so every thread has its own browser.
- **Configuration file:** environment settings are read from `config.properties`.
- **Allure report:** test cases, results and steps, generated as a static HTML report.

## Test cases

| # | Module | Scenario |
|---|---|---|
| TC1 | Login | Login with valid credentials |
| TC2 | Login | Login with invalid credentials |
| TC3 | Login | Login with empty fields |
| TC4 | PIM | Search for an existing employee |
| TC5 | PIM | Search for a non-existing employee |
| TC6 | PIM | Open the Add Employee page |
| TC7 | PIM | Add an employee with an empty required field |
| TC8 | PIM | End-to-end: add a new employee and find it in the Employee List |
| TC9 | Admin | Verify the Admin > Add User page |
| TC10 | UI | Verify the OrangeHRM footer/branding link |
| TC11 | UI | Verify the sidebar menu |

## Project structure

```
automation-testing-graduation-project/
├── pom.xml
├── testng.xml                      # suite: parallel="tests", thread-count="3"
├── allure-report/                  # generated static Allure report
└── src/
    └── ...
        ├── base/                   # Driver (ThreadLocal), BaseTest, BasePage
        ├── pages/                  # LoginPage, HomePage, PIMPage
        ├── tests/                  # LoginTest, HomeTest, PIMTest
        ├── utils/                  # ConfigReader, JSON reader
        └── resources/
            ├── config.properties
            └── DataDriven/testdata.json
```

## Prerequisites

- JDK 21
- Maven 3.9+
- Google Chrome (or Microsoft Edge)
- [Allure Commandline](https://allurereport.org/docs/install/) (only needed to generate and open the report)

## Configuration

Settings are in `src/test/resources/config.properties`:

```properties
base.url=https://opensource-demo.orangehrmlive.com/web/index.php/
browser=chrome
explicit.wait=10
first.name=Seif
last.name=Ahmed
```

Test data (usernames, passwords, employee names) is in `src/test/resources/DataDriven/testdata.json`.

## How to run the tests

```bash
mvn clean test
```

This runs the suite defined in `testng.xml` and writes the raw results to `allure-results/`.

## Allure report

Generate the static HTML report:

```bash
allure generate allure-results --clean -o allure-report
```

Open it locally:

```bash
allure open allure-report
```

The generated `allure-report/` folder is committed to this repository. The `allure-results/` folder is not.

## Login used by the tests

The demo site is public. Credentials: username `Admin`, password `admin123`.

## Author

**Seif Ahmed** (Junior Software Test Engineer)
