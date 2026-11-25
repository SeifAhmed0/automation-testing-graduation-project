package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By loaderLoc = By.cssSelector(".oxd-form-loader");

    protected void waitForLoaderToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLoc));
    }

    protected void clickWhenReady(By locator) {
        waitForLoaderToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
