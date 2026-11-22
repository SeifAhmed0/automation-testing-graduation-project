package tests;

import base.BaseTest;
import base.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PIMPage;

public class PIMTest extends BaseTest {
    //TC4
    @Test
    public void verifySearchExistingEmp(){
        WebDriver driver = Driver.getDriver();
        LoginTest lt = new LoginTest();
        HomePage hp = new HomePage(driver);
        PIMPage pp = new PIMPage(driver);
        lt.verifyValidLogin();
        Assert.assertTrue(hp.isDashboardDisplayed());
        hp.pimNavigator();
        Assert.assertTrue(pp.empListPageVerification(), "Incorrect Page!");
        Assert.assertTrue(pp.searchExistingEmp(), "Employee Not Found!");
    }

    //TC5
    @Test
    public void verifySearchNonExistingEmp(){
        WebDriver driver = Driver.getDriver();
        LoginTest lt = new LoginTest();
        HomePage hp = new HomePage(driver);
        PIMPage pp = new PIMPage(driver);
        lt.verifyValidLogin();
        Assert.assertTrue(hp.isDashboardDisplayed());
        hp.pimNavigator();
        Assert.assertTrue(pp.empListPageVerification(), "Incorrect Page!");
        Assert.assertTrue(pp.searchNonExistingEmp(), "Not Expected Message!");
    }

    //TC6
    @Test
    public void verifyAddEmpPage(){
        WebDriver driver = Driver.getDriver();
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        PIMPage pp = new PIMPage(driver);
        lp.validLogin();
        Assert.assertTrue(hp.isDashboardDisplayed());
        hp.pimNavigator();
        Assert.assertTrue(pp.empListPageVerification());
        Assert.assertTrue(pp.addEmpPage());
        Assert.assertTrue(driver.getCurrentUrl().contains("/pim/addEmployee"));
    }

    //TC7
    @Test
    public void verifyAddEmpWithEmptyFN(){
        WebDriver driver = Driver.getDriver();
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        PIMPage pp = new PIMPage(driver);
        lp.validLogin();
        Assert.assertTrue(hp.isDashboardDisplayed());
        hp.pimNavigator();
        Assert.assertTrue(pp.empListPageVerification());
        Assert.assertTrue(pp.addEmpPage());
        Assert.assertTrue(driver.getCurrentUrl().contains("/pim/addEmployee"));
        Assert.assertTrue(pp.addEmpWithEmptyFN());
    }

    //TC8
//    @Test
//    public void verifyAddEmpSuccessfully() {
//        WebDriver driver = Driver.getDriver();
//        LoginPage lp = new LoginPage(driver);
//        HomePage hp = new HomePage(driver);
//        PIMPage pp = new PIMPage(driver);
//        lp.verifyValidLogin();
//        Assert.assertTrue(hp.isDashboardDisplayed());
//        hp.pimNavigator();
//        Assert.assertTrue(pp.empListPageVerification());
//        Assert.assertTrue(pp.addEmpPage());
//        List<WebElement> addEmpList = pp.addEmpSuccessfullyPage();
//        addEmpList.get(0).sendKeys(ConfigReader.get("first.name"));
//        addEmpList.get(1).sendKeys(ConfigReader.get("last.name"));
//        addEmpList.get(2).click();
//    }

    //TC9
    @Test
    public void verifyAddUserPage(){
        WebDriver driver = Driver.getDriver();
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        PIMPage pp = new PIMPage(driver);
        lp.validLogin();
        Assert.assertTrue(hp.isDashboardDisplayed());
        Assert.assertTrue(pp.addUserPage());
    }
}
