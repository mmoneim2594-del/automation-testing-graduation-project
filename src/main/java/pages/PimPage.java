package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PimPage extends BasePage {
    private By employeeNameField = By.xpath("//label[normalize-space()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");
    private By resultRows = By.cssSelector("div.oxd-table-body div.oxd-table-card");
    private By noRecordsMessage = By.xpath("//span[normalize-space()='No Records Found']");

    public PimPage(WebDriver driver){
        super(driver);
    }

    public PimPage openEmployeeList(){
        clickMenu("PIM");
        clickTopTab("Employee List");
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        return this;
    }

    public void setEmployeeNameField(String name){
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameField)).sendKeys(name);
    }

    public void clickSearchButton(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public PimPage searchEmployee(String name){
        setEmployeeNameField(name);
        clickSearchButton();
        return this;
    }

    public boolean resultsContain(String name){
        try {
            return wait.until(d -> {
                for(WebElement row : d.findElements(resultRows)){
                    if(row.getText().toLowerCase().contains(name.toLowerCase())){
                        return true;
                    }
                }
                return false;
            });
        } catch (TimeoutException e){
            return false;
        }
    }

    public boolean isNoRecordsFoundDisplayed(){
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsMessage)).isDisplayed();
        } catch (TimeoutException e){
            return false;
        }
    }
}