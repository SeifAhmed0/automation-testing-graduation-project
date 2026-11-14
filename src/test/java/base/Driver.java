package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Driver {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void initDriver(String browser){
        switch (browser.toLowerCase()){
            case "edge":
                driver.set(new EdgeDriver());
                break;
            case "chrome":
            default:
                driver.set(new ChromeDriver());
        }
        getDriver().manage().window().maximize();
    }

    public static void quitDriver(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }

}
