package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.jackson.databind.JsonNode;
import utils.DataProvider;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage {
    private final By usernameInputLoc = By.cssSelector("input[placeholder=\"Username\"]");
    private final By passwordInputLoc = By.cssSelector("input[placeholder=\"Password\"]");
    private final By loginBtnLoc = By.className("oxd-button");
    private final By errorMsgLoc = By.cssSelector("p[class=\"oxd-text oxd-text--p oxd-alert-content-text\"]");
    private final By requiredMsgLoc = By.className("oxd-input-field-error-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private void login(WebDriverWait wait, JsonNode loginData) {
        String username = loginData.get("Username").asString();
        String password = loginData.get("Password").asString();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInputLoc)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputLoc)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginBtnLoc)).click();
    }

    public void verifyValidLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            JsonNode data = DataProvider.JsonReader();
            JsonNode validLogin = data.get("ValidLogin");
            login(wait, validLogin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyInvalidLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            JsonNode data = DataProvider.JsonReader();
            JsonNode invalidLogin = data.get("InvalidLogin");
            login(wait, invalidLogin);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgLoc)).isDisplayed();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean verifyEmptyLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            JsonNode data = DataProvider.JsonReader();
            JsonNode emptyLogin = data.get("EmptyLogin");
            login(wait, emptyLogin);
            boolean firstRequired = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(requiredMsgLoc)).getFirst().isDisplayed();
            boolean secondRequired = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(requiredMsgLoc)).getLast().isDisplayed();
            if (firstRequired == secondRequired == true) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
