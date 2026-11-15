package tests;

import base.BaseTest;
import base.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 0)
    public void verifyValidLogin(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        lp.verifyValidLogin();
        boolean actualResult = driver.getCurrentUrl().contains("/dashboard/index");
        soft.assertTrue(actualResult);
        soft.assertTrue(hp.isDashboardDisplayed());
    }

    @Test(priority = 1)
    public void verifyInvalidLogin(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        soft.assertTrue(lp.verifyInvalidLogin());
    }

    @Test(priority = 2)
    public void verifyEmptyLogin(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        soft.assertTrue(lp.verifyEmptyLogin());
    }
}
