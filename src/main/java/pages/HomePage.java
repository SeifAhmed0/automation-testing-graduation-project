package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    private final By dashboardLoc = By.tagName("h6");
    private final By pimBtnLoc = By.xpath("(//a[@class=\"oxd-main-menu-item\"])[2]");
    private final By adminLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[1]");
    private final By pimLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[2]");
    private final By leaveLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[3]");
    private final By timeLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[4]");
    private final By recruitmentLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[5]");
    private final By myInfoLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[6]");
    private final By performanceLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[7]");
    private final By dashboardLoc1 = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[8]");
    private final By directoryLoc = By.xpath("(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[9]");

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

    public boolean sidebarMenu(){
        int count = 0;
        boolean admin = wait.until(ExpectedConditions.visibilityOfElementLocated(adminLoc)).isDisplayed();
        boolean pim = wait.until(ExpectedConditions.visibilityOfElementLocated(pimLoc)).isDisplayed();
        boolean leave = wait.until(ExpectedConditions.visibilityOfElementLocated(leaveLoc)).isDisplayed();
        boolean time = wait.until(ExpectedConditions.visibilityOfElementLocated(timeLoc)).isDisplayed();
        boolean recruitment = wait.until(ExpectedConditions.visibilityOfElementLocated(recruitmentLoc)).isDisplayed();
        boolean myInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(myInfoLoc)).isDisplayed();
        boolean performance = wait.until(ExpectedConditions.visibilityOfElementLocated(performanceLoc)).isDisplayed();
        boolean dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardLoc1)).isDisplayed();
        boolean directory = wait.until(ExpectedConditions.visibilityOfElementLocated(directoryLoc)).isDisplayed();

        List<Boolean> menuItemsList = new ArrayList<>();
        menuItemsList.add(admin);
        menuItemsList.add(pim);
        menuItemsList.add(leave);
        menuItemsList.add(time);
        menuItemsList.add(recruitment);
        menuItemsList.add(myInfo);
        menuItemsList.add(performance);
        menuItemsList.add(dashboard);
        menuItemsList.add(directory);

        for (int i = 0; i < menuItemsList.size(); i++){
            if (menuItemsList.get(i) == true){
                count++;
            }
        }
        if (count == 9){
            return true;
        }
        return false;
    }
}
