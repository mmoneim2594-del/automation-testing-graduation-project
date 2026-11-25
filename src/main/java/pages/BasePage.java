package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class BasePage {
    protected final WebDriverWait wait;
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait")));
    }

    public void clicklink(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }


    public void clickMenu(String name){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//a[contains(@class,'oxd-main-menu-item')][.//span[normalize-space()='" + name + "']]"))).click();
    }


    public void clickTopTab(String name){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//a[contains(@class,'oxd-topbar-body-nav-tab-item')][normalize-space()='" + name + "']"))).click();
    }
}