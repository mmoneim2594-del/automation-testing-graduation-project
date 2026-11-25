package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void initDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        DRIVER.set(driver);          // store this thread's browser
    }

    public static WebDriver getDriver() {
        return DRIVER.get();         // get this thread's browser
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();     // close the browser
            DRIVER.remove();         // forget it
        }
    }
}
