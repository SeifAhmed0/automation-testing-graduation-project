package tests;

import base.BaseTest;
import base.Driver;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

@Epic("OrangeHRM")
public class HomeTest extends BaseTest {

    //TC11
    @Test(description = "TC11 - Verify sidebar menu UI")
    @Feature("Navigation")
    @Story("Sidebar menu")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Seif Ahmed")
    @TmsLink("TC-11")
    @Description("Log in and verify the sidebar contains: Admin, PIM, Leave, Time, Recruitment, My Info, Performance, Dashboard, Directory.")
    public void verifySidebarMenu(){
        WebDriver driver = Driver.getDriver();
        HomePage hp = new HomePage(driver);
        LoginPage lp = new LoginPage(driver);
        lp.validLogin();
        Assert.assertTrue(hp.sidebarMenu());
    }
}
