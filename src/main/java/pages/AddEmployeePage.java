package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddEmployeePage extends BasePage {
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.cssSelector("button[type='submit']");
    private By firstNameError = By.xpath("//input[@name='firstName']/ancestor::div[contains(@class,'oxd-input-group')]//span[contains(@class,'oxd-input-field-error-message')]");
    private By personalDetailsHeader = By.xpath("//h6[normalize-space()='Personal Details']");

    public AddEmployeePage(WebDriver driver){
        super(driver);
    }

    public AddEmployeePage open(){
        clickMenu("PIM");
        clickTopTab("Add Employee");
        wait.until(ExpectedConditions.urlContains("/pim/addEmployee"));
        return this;
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public boolean isFirstNameDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).isDisplayed();
    }

    public boolean isLastNameDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).isDisplayed();
    }

    public void setFirstNameField(String firstName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).sendKeys(firstName);
    }

    public void setLastNameField(String lastName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).sendKeys(lastName);
    }

    public void clickSaveButton(){
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public String getFirstNameError(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameError)).getText();
    }

    public boolean isPersonalDetailsOpened(){
        wait.until(ExpectedConditions.urlContains("/pim/viewPersonalDetails"));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader)).isDisplayed();
    }
}