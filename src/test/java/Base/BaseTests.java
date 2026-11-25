package Base;

import io.qameta.allure.Allure;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DataProviders;

public class BaseTests {
    protected BasePage base;

    @BeforeClass
    public void setUp(){
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("base.url") + "auth/login");
        base = new BasePage(DriverFactory.getDriver());
    }

    @BeforeMethod
    public void gohome(){
        DriverFactory.getDriver().manage().deleteAllCookies();   // log out, so the login page opens again
        DriverFactory.getDriver().get(ConfigReader.get("base.url") + "auth/login");
        base = new BasePage(DriverFactory.getDriver());
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }

    // Used by every test that must be logged in first
    protected void loginAsAdmin(){
        Allure.step("Log in as Admin");
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.setUsernameField(DataProviders.getString("validUser", "username"));
        loginPage.setPasswordField(DataProviders.getString("validUser", "password"));
        loginPage.clickLoginButton();
    }
}