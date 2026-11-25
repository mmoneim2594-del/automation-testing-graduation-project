package Admin;

import Base.BaseTests;
import Base.DriverFactory;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;

public class AdminTests extends BaseTests {

    @Test(description = "TC9: Verify Admin > Add User page")
    public void testAddUserForm() {
        loginAsAdmin();
        AdminPage adminPage = new AdminPage(DriverFactory.getDriver());

        Allure.step("Go to Admin > User Management > Users");
        adminPage.openUsers();

        Allure.step("Click Add");
        adminPage.clickAddButton();

        Allure.step("Assert the form fields are displayed");
        Assert.assertTrue(adminPage.isFieldDisplayed("User Role"));
        Assert.assertTrue(adminPage.isFieldDisplayed("Employee Name"));
        Assert.assertTrue(adminPage.isFieldDisplayed("Username"));
        Assert.assertTrue(adminPage.isFieldDisplayed("Password"));
    }
}