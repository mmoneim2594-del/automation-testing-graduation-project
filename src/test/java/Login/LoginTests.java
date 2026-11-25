package Login;

import Base.BaseTests;
import Base.DriverFactory;
import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.DataProviders;

import java.util.List;

import static org.testng.Assert.*;

public class LoginTests extends BaseTests {

    @Test(description = "TC1: Login with valid credentials")
    public void testSuccessfulLogin(){
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        Allure.step("Enter username and password");
        loginPage.setUsernameField(DataProviders.getString("validUser", "username"));
        loginPage.setPasswordField(DataProviders.getString("validUser", "password"));

        Allure.step("Click Login");
        DashboardPage dashboardPage = loginPage.clickLoginButton();

        Allure.step("Assert the URL and the Dashboard header");
        assertTrue(loginPage.isRedirected(), "User isn't redirected");
        assertEquals(dashboardPage.getHeaderText(), "Dashboard");
    }

    @Test(description = "TC2: Login with invalid credentials",
            dataProvider = "invalidLogin", dataProviderClass = DataProviders.class)
    public void testInvalidLogin(String username, String password){
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        Allure.step("Enter invalid credentials");
        loginPage.setUsernameField(username);
        loginPage.setPasswordField(password);

        Allure.step("Click Login");
        loginPage.clickLoginButton();

        Allure.step("Assert the error message");
        assertEquals(loginPage.getErrorMessage(), "Invalid credentials");
    }

    @Test(description = "TC3: Login with empty fields")
    public void testEmptyLogin(){
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

        Allure.step("Click Login with empty fields");
        loginPage.clickLoginButton();

        Allure.step("Assert 'Required' appears under both fields");
        List<String> messages = loginPage.getReqMessage();
        assertEquals(messages.size(), 2);
        assertEquals(messages.get(0), "Required");
        assertEquals(messages.get(1), "Required");
    }
}