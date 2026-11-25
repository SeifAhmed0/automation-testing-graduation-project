package pages;

import base.BasePage;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.jackson.databind.JsonNode;
import utils.DataProvider;

import java.io.IOException;
import java.time.Duration;

public class PIMPage extends BasePage {
    private final WebDriverWait wait;
    private final By empInfoTitleLoc = By.className("oxd-table-filter-title");
    private final By empNameFieldLoc = By.xpath("(//input[@placeholder=\"Type for hints...\"])[1]");
    private final By searchBtnLoc = By.className("orangehrm-left-space");
    private final By empNameRecordLoc = By.xpath("//div[@role=\"cell\"]//div[normalize-space()=\"Charles\"]");
    private final By noRecordsFoundMsgLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span\"])[1]");
    private final By addEmpBtnLoc = By.xpath("//a[@class=\"oxd-topbar-body-nav-tab-item\"][.=\"Add Employee\"]");
    private final By firstNameFieldLoc = By.name("firstName");
    private final By lastNameFieldLoc = By.name("lastName");
    private final By saveBtnLoc = By.className("oxd-button--secondary");
    private final By missingFNErrorMsgLoc = By.className("oxd-input-field-error-message");
    private final By employeeListBtnLoc = By.xpath("//a[@class=\"oxd-topbar-body-nav-tab-item\"][.=\"Employee List\"]");
    private final By personalFN = By.cssSelector("input[placeholder=\"First Name\"]");
    private final By adminLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[1]");
    private final By addUserBtnLoc = By.cssSelector("button[class=\"oxd-button oxd-button--medium oxd-button--secondary\"]");
    private final By userRoleLoc = By.xpath("(//label[@class=\"oxd-label oxd-input-field-required\"])[1]");
    private final By empNameLoc = By.xpath("(//label[@class=\"oxd-label oxd-input-field-required\"])[2]");
    private final By userNameLoc = By.xpath("(//label[@class=\"oxd-label oxd-input-field-required\"])[4]");
    private final By passwordLoc = By.xpath("(//label[@class=\"oxd-label oxd-input-field-required\"])[5]");
    private final By submitBtnLoc = By.cssSelector("button[type='submit']");


    public PIMPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public boolean empListPageVerification() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(empInfoTitleLoc)).isDisplayed();
    }

    public boolean searchExistingEmp() {
        try {
            JsonNode data = DataProvider.JsonReader();
            JsonNode EmployeeNames = data.get("EmployeeNames");
            wait.until(ExpectedConditions.elementToBeClickable(empNameFieldLoc)).sendKeys(EmployeeNames.get(0).asString());
            wait.until(ExpectedConditions.elementToBeClickable(searchBtnLoc)).click();
            String empName = wait.until(ExpectedConditions.visibilityOfElementLocated(empNameRecordLoc)).getAttribute("textContent").trim().toLowerCase();
            if (empName.equals(EmployeeNames.get(0).asString().trim().toLowerCase())) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean searchNonExistingEmp() {
        try {
            JsonNode data = DataProvider.JsonReader();
            JsonNode EmployeeNames = data.get("EmployeeNames");
            wait.until(ExpectedConditions.elementToBeClickable(empNameFieldLoc)).sendKeys(EmployeeNames.get(1).asString());
            wait.until(ExpectedConditions.elementToBeClickable(searchBtnLoc)).click();
            driver.findElement(searchBtnLoc).click();
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsFoundMsgLoc)).isDisplayed()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addEmpPage() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmpBtnLoc)).click();
        boolean fn = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFieldLoc)).isDisplayed();
        boolean ln = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFieldLoc)).isDisplayed();
        if (fn == ln == true) {
            return true;
        }
        return false;
    }

    public boolean addEmpWithEmptyFN() {
        try {
            JsonNode data = DataProvider.JsonReader();
            JsonNode newEmployee = data.get("NewEmployee");
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFieldLoc)).sendKeys(newEmployee.get("FirstName").asString());
            wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFieldLoc)).sendKeys(newEmployee.get("LastName").asString());
            driver.findElement(saveBtnLoc).click();
            boolean isErrorMsgDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(missingFNErrorMsgLoc)).isDisplayed();
            if (isErrorMsgDisplayed) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Step("Enter employee name: first '{firstName}', last '{lastName}'")
    public void enterEmployeeName(String firstName, String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFieldLoc)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFieldLoc)).sendKeys(lastName);
    }

    @Step("Click Save")
    public void clickSave() {
        clickWhenReady(submitBtnLoc);
    }

    @Step("Verify the Personal Details page opened for '{firstName}'")
    public boolean isPersonalDetailsOpenedFor(String firstName) {
        wait.until(ExpectedConditions.urlContains("/pim/viewPersonalDetails/empNumber/"));
        waitForLoaderToDisappear();
        // the field is filled by the page after it loads, so wait for its value
        return wait.until(ExpectedConditions.attributeToBe(personalFN, "value", firstName));
    }

    @Step("Go back to Employee List")
    public void goToEmployeeList() {
        clickWhenReady(employeeListBtnLoc);
        wait.until(ExpectedConditions.visibilityOfElementLocated(empInfoTitleLoc));
    }

    @Step("Search for employee '{name}'")
    public void searchEmployee(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(empNameFieldLoc)).sendKeys(name);
        clickWhenReady(submitBtnLoc);
    }

    @Step("Verify '{name}' appears in the results table")
    public boolean isEmployeeInResults(String name) {
        By cell = By.xpath("//div[@role='table']//div[@role='cell']//div[contains(normalize-space(), '" + name + "')]");
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(cell, name));
    }

    public boolean addUserPage(){
        wait.until(ExpectedConditions.elementToBeClickable(adminLoc)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addUserBtnLoc)).click();
        boolean userRole = wait.until(ExpectedConditions.visibilityOfElementLocated(userRoleLoc)).isDisplayed();
        boolean empName = wait.until(ExpectedConditions.visibilityOfElementLocated(empNameLoc)).isDisplayed();
        boolean userName = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLoc)).isDisplayed();
        boolean password = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoc)).isDisplayed();
        return userRole == empName == userName == password == true;
    }
}
