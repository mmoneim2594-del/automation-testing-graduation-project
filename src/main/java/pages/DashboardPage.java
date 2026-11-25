package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class DashboardPage extends BasePage {
    private By header = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module");
    private By sidebarItems = By.cssSelector("span.oxd-main-menu-item--name");

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    public boolean isDashboardUrl(){
        return wait.until(ExpectedConditions.urlContains("/dashboard/index"));
    }

    public boolean isHeaderDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).isDisplayed();
    }

    public String getHeaderText(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
    }

    public List<String> getSidebarItems(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(sidebarItems));
        List<String> items = new ArrayList<>();
        for(WebElement item : driver.findElements(sidebarItems)){
            items.add(item.getText());
        }
        return items;
    }
}