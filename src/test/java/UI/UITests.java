package UI;

import Base.BaseTests;
import Base.DriverFactory;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

import java.util.List;

public class UITests extends BaseTests {

    @Test(description = "TC10: Verify the OrangeHRM footer/branding link")
    public void testFooterLink() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        Allure.step("Open the login page");
        loginPage.open();

        Allure.step("Assert the footer contains 'OrangeHRM, Inc'");
        Assert.assertTrue(loginPage.getFooterText().contains("OrangeHRM, Inc"));

        Allure.step("Click the link and check the new tab URL");
        String newTabUrl = loginPage.clickFooterLinkAndGetNewTabUrl();
        Assert.assertTrue(newTabUrl.contains("orangehrm.com"));
    }

    @Test(description = "TC11: Verify sidebar menu UI")
    public void testSidebarMenu() {
        loginAsAdmin();
        DashboardPage dashboardPage = new DashboardPage(DriverFactory.getDriver());

        Allure.step("Read the sidebar items");
        List<String> items = dashboardPage.getSidebarItems();

        Allure.step("Assert all expected items are present");
        for (String name : new String[]{"Admin", "PIM", "Leave", "Time", "Recruitment",
                "My Info", "Performance", "Dashboard", "Directory"}) {
            Assert.assertTrue(items.contains(name), "Sidebar should contain: " + name);
        }
    }
}