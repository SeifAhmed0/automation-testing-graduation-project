package tests;

import base.BaseTest;
import base.Driver;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PIMPage;
import utils.ConfigReader;

@Epic("OrangeHRM")
public class PIMTest extends BaseTest {
    //TC4
    @Test(description = "TC4 - Search for an existing employee (PIM)")
    @Feature("PIM")
    @Story("Search employee")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Seif Ahmed")
    @TmsLink("TC-4")
    @Description("Log in, open PIM > Employee List, search for a known employee name and verify the results table contains it.")
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
    @Test(description = "TC5 - Search for a non-existing employee")
    @Feature("PIM")
    @Story("Search non-existing employee")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Seif Ahmed")
    @TmsLink("TC-5")
    @Description("Search for a random name in Employee List and verify the 'No Records Found' message is displayed.")
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
    @Test(description = "TC6 - Open Add Employee page")
    @Feature("PIM")
    @Story("Open Add Employee page")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Seif Ahmed")
    @TmsLink("TC-6")
    @Description("From the PIM menu click Add Employee and verify the URL contains /pim/addEmployee and the First Name and Last Name fields are displayed.")
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
    @Test(description = "TC7 - Add a new employee with an empty required field")
    @Feature("PIM")
    @Story("Add employee validation")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Seif Ahmed")
    @TmsLink("TC-7")
    @Description("Enter only the Last Name, leave First Name empty, click Save and verify the 'Required' validation message is displayed under First Name.")
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
    @Test(description = "TC8 - Add a new employee successfully (end-to-end)")
    @Feature("PIM")
    @Story("Add employee end-to-end")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Seif Ahmed")
    @TmsLink("TC-8")
    @Description("Add a new employee, verify the Personal Details page opens, then find the employee in the Employee List.")
    public void verifyAddEmpSuccessfully() {
        WebDriver driver = Driver.getDriver();
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        PIMPage pp = new PIMPage(driver);

        // unique name, so reruns never clash and the search can only match this employee
        String firstName = ConfigReader.get("first.name") + System.currentTimeMillis();
        String lastName = ConfigReader.get("last.name");

        lp.validLogin();                                                   // Log in
        Assert.assertTrue(hp.isDashboardDisplayed(), "Dashboard was not displayed after login");
        hp.pimNavigator();                                                 // Navigate to PIM
        Assert.assertTrue(pp.empListPageVerification(), "Incorrect Page!");
        Assert.assertTrue(pp.addEmpPage(), "Add Employee page was not displayed");
        pp.enterEmployeeName(firstName, lastName);                         // Enter first and last name
        pp.clickSave();                                                    // Click Save
        Assert.assertTrue(pp.isPersonalDetailsOpenedFor(firstName),
                "Personal Details page did not open for " + firstName);    // Assert Personal Details
        pp.goToEmployeeList();                                             // Back to Employee List
        pp.searchEmployee(firstName);                                      // Search for the new employee
        Assert.assertTrue(pp.isEmployeeInResults(firstName),
                "New employee '" + firstName + "' was not found in the results");
    }

    //TC9
    @Test(description = "TC9 - Verify Admin > Add User page")
    @Feature("Admin")
    @Story("Add User form")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Seif Ahmed")
    @TmsLink("TC-9")
    @Description("Open Admin > User Management > Users, click Add and verify the form has User Role, Employee Name, Username and Password fields.")
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
