package tests;

import base.BaseTest;
import base.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PIMPage;

public class PIMTest extends BaseTest {
    @Test
    public void verifySearchExistedEmp(){
        WebDriver driver = Driver.getDriver();
        LoginTest lt = new LoginTest();
        HomePage hp = new HomePage(driver);
        PIMPage pp = new PIMPage(driver);
        lt.verifyValidLogin();
        Assert.assertTrue(hp.isDashboardDisplayed());
        hp.pimNavigator();
        Assert.assertTrue(pp.empListPageVerification(), "Incorrect Page!");
        Assert.assertTrue(pp.searchExistedEmp(), "Employee Not Found!");
    }

    @Test
    public void verifySearchNotExistedEmp(){
        WebDriver driver = Driver.getDriver();
        LoginTest lt = new LoginTest();
        HomePage hp = new HomePage(driver);
        PIMPage pp = new PIMPage(driver);
        lt.verifyValidLogin();
        Assert.assertTrue(hp.isDashboardDisplayed());
        hp.pimNavigator();
        Assert.assertTrue(pp.empListPageVerification(), "Incorrect Page!");
        Assert.assertTrue(pp.searchNotExistedEmp(), "Not Expected Message!");
    }
}
