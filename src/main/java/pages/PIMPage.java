package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.jackson.databind.JsonNode;
import utils.DataProvider;

import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;

public class PIMPage extends BasePage {
    private final By empInfoTitleLoc = By.className("oxd-table-filter-title");
    private final By empNameFieldLoc = By.xpath("(//input[@placeholder=\"Type for hints...\"])[1]");
    private final By searchBtnLoc = By.className("orangehrm-left-space");
    private final By empNameRecordLoc = By.xpath("//div[@role=\"cell\"]//div[normalize-space()=\"ben\"]");
    private final By noRecordsFoundMsgLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span\"])[1]");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public boolean empListPageVerification(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(empInfoTitleLoc)).isDisplayed();
    }

    public boolean searchExistedEmp(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            JsonNode data = DataProvider.JsonReader();
            JsonNode EmployeeNames = data.get("EmployeeNames");
            wait.until(ExpectedConditions.elementToBeClickable(empNameFieldLoc)).sendKeys(EmployeeNames.get(0).asString());
            wait.until(ExpectedConditions.elementToBeClickable(searchBtnLoc)).click();
            driver.findElement(searchBtnLoc).click();
            String empName = wait.until(ExpectedConditions.visibilityOfElementLocated(empNameRecordLoc)).getAttribute("textContent").trim().toLowerCase();
            if (empName.equals(EmployeeNames.get(0).asString().trim().toLowerCase())){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean searchNotExistedEmp(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            JsonNode data = DataProvider.JsonReader();
            JsonNode EmployeeNames = data.get("EmployeeNames");
            wait.until(ExpectedConditions.elementToBeClickable(empNameFieldLoc)).sendKeys(EmployeeNames.get(1).asString());
            wait.until(ExpectedConditions.elementToBeClickable(searchBtnLoc)).click();
            driver.findElement(searchBtnLoc).click();
//            String empName = wait.until(ExpectedConditions.visibilityOfElementLocated(empNameRecordLoc)).getAttribute("textContent").trim().toLowerCase();
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsFoundMsgLoc)).isDisplayed()){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
