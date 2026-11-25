package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPage extends BasePage {
    private By userManagementMenu = By.xpath("//span[normalize-space()='User Management']");
    private By usersOption = By.xpath("//a[normalize-space()='Users']");
    private By addButton = By.xpath("//button[normalize-space()='Add']");

    public AdminPage(WebDriver driver){
        super(driver);
    }

    public AdminPage openUsers(){
        clickMenu("Admin");
        wait.until(ExpectedConditions.elementToBeClickable(userManagementMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(usersOption)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addButton));
        return this;
    }

    public AdminPage clickAddButton(){
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        return this;
    }

    public boolean isFieldDisplayed(String label){
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[normalize-space()='" + label + "']"))).isDisplayed();
        } catch (TimeoutException e){
            return false;
        }
    }
}