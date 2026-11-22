package tests;

import base.BaseTest;
import base.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    //TC1
    @Test(priority = 0)
    public void verifyValidLogin(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        lp.validLogin();
        boolean actualResult = driver.getCurrentUrl().contains("/dashboard/index");
        soft.assertTrue(actualResult);
        soft.assertTrue(hp.isDashboardDisplayed());
        soft.assertAll();
    }

    //TC2
    @Test(priority = 1)
    public void verifyInvalidLogin(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        soft.assertTrue(lp.invalidLogin());
        soft.assertAll();
    }

    //TC3
    @Test(priority = 2)
    public void verifyEmptyLogin(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        soft.assertTrue(lp.emptyLogin());
        soft.assertAll();
    }

    //TC10
    @Test(priority = 3)
    public void verifyOrangeHRMFooterBrandingLink(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        lp.validLogin();
        soft.assertTrue(lp.orangeHRMFooter());
        lp.orangeHRMFooterLinkToClick();
        soft.assertTrue(driver.getCurrentUrl().contains("orangehrm.com"), "Expected New Tab Title");
        driver.close();
        soft.assertAll();
    }
}
