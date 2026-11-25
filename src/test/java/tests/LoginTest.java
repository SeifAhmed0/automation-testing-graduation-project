package tests;

import base.BaseTest;
import base.Driver;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;

@Epic("OrangeHRM")
public class LoginTest extends BaseTest {
    //TC1
    @Test(priority = 0, description = "TC1 - Login with valid credentials")
    @Feature("Login")
    @Story("Valid login")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Seif Ahmed")
    @TmsLink("TC-1")
    @Description("Log in with the Admin account, then verify the URL contains /dashboard/index and the Dashboard header is displayed.")
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
    @Test(priority = 1, description = "TC2 - Login with invalid credentials")
    @Feature("Login")
    @Story("Invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Seif Ahmed")
    @TmsLink("TC-2")
    @Description("Log in with a wrong username and password and verify the 'Invalid credentials' error message is displayed.")
    public void verifyInvalidLogin(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        soft.assertTrue(lp.invalidLogin());
        soft.assertAll();
    }

    //TC3
    @Test(priority = 2, description = "TC3 - Login with empty fields")
    @Feature("Login")
    @Story("Empty fields")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Seif Ahmed")
    @TmsLink("TC-3")
    @Description("Click Login with empty username and password and verify the 'Required' message appears under both fields.")
    public void verifyEmptyLogin(){
        WebDriver driver = Driver.getDriver();
        SoftAssert soft = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        soft.assertTrue(lp.emptyLogin());
        soft.assertAll();
    }

    //TC10
    @Test(priority = 3, description = "TC10 - Verify the OrangeHRM footer/branding link")
    @Feature("Branding")
    @Story("Footer link")
    @Severity(SeverityLevel.MINOR)
    @Owner("Seif Ahmed")
    @TmsLink("TC-10")
    @Description("Verify the footer contains 'OrangeHRM, Inc', click it and verify the new tab URL contains orangehrm.com.")
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
