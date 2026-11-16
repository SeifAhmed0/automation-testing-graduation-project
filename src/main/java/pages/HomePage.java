package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    private final By dashboardLoc = By.tagName("h6");
    private final By pimBtnLoc = By.xpath("(//a[@class=\"oxd-main-menu-item\"])[2]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardLoc)).isDisplayed();
    }

    public void pimNavigator(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(pimBtnLoc)).click();
    }
}
