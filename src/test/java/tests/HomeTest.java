package tests;

import base.BaseTest;
import base.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomeTest extends BaseTest {

    //TC11
    @Test
    public void verifySidebarMenu(){
        WebDriver driver = Driver.getDriver();
        HomePage hp = new HomePage(driver);
        LoginPage lp = new LoginPage(driver);
        lp.validLogin();
        Assert.assertTrue(hp.sidebarMenu());
    }
}
