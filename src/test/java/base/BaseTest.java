package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup(@Optional("edge") String browser){
        Driver.initDriver(ConfigReader.get("browser"));
        Driver.getDriver().get(ConfigReader.get("base.url"));
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        Driver.quitDriver();
    }
}
