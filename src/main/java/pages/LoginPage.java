package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends BasePage {
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By errorMessage = By.cssSelector(".oxd-alert-content-text");
    private By reqMessage = By.cssSelector("span.oxd-input-field-error-message");
    private By footer = By.cssSelector("div.orangehrm-login-footer");
    private By footerLink = By.xpath("//div[contains(@class,'orangehrm-login-footer')]//a[contains(.,'OrangeHRM, Inc')]");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public LoginPage open(){
        driver.get(ConfigReader.get("base.url") + "auth/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        return this;
    }

    public void setUsernameField(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    public void setPasswordField(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public DashboardPage clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new DashboardPage(driver);
    }

    public String getErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public List<String> getReqMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(reqMessage));
        List<String> texts = new ArrayList<>();
        for(WebElement message : driver.findElements(reqMessage)){
            texts.add(message.getText());
        }
        return texts;
    }

    public boolean isRedirected(){
        return wait.until(ExpectedConditions.urlContains("/dashboard/index"));
    }

    public String getFooterText(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(footer));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element.getText();
    }

    public String clickFooterLinkAndGetNewTabUrl(){
        String original = driver.getWindowHandle();
        wait.until(ExpectedConditions.elementToBeClickable(footerLink)).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for(String handle : driver.getWindowHandles()){
            if(!handle.equals(original)){
                driver.switchTo().window(handle);
            }
        }
        wait.until(ExpectedConditions.urlContains("orangehrm.com"));
        String url = driver.getCurrentUrl();
        driver.close();                          // close the new tab
        driver.switchTo().window(original);      // go back to the first tab
        return url;
    }
}