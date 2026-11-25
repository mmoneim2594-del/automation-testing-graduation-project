package PIM;

import Base.BaseTests;
import Base.DriverFactory;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.PimPage;
import utils.DataProviders;

public class PimTests extends BaseTests {

    @Test(description = "TC4: Search for an employee",
            dataProvider = "searchNames", dataProviderClass = DataProviders.class)
    public void testSearchEmployee(String name) {
        loginAsAdmin();
        PimPage pimPage = new PimPage(DriverFactory.getDriver());

        Allure.step("Go to PIM > Employee List");
        pimPage.openEmployeeList();

        Allure.step("Search for: " + name);
        pimPage.searchEmployee(name);

        Allure.step("Assert the results contain the employee");
        Assert.assertTrue(pimPage.resultsContain(name), "Results should contain: " + name);
    }

    @Test(description = "TC5: Search for a non-existing employee")
    public void testSearchNonExistingEmployee() {
        loginAsAdmin();
        PimPage pimPage = new PimPage(DriverFactory.getDriver());

        Allure.step("Go to PIM > Employee List");
        pimPage.openEmployeeList();

        Allure.step("Search for an unknown name");
        pimPage.searchEmployee(DataProviders.getString("unknownEmployee"));

        Allure.step("Assert 'No Records Found' is displayed");
        Assert.assertTrue(pimPage.isNoRecordsFoundDisplayed());
    }

    @Test(description = "TC6: Open Add Employee page")
    public void testOpenAddEmployeePage() {
        loginAsAdmin();
        AddEmployeePage addPage = new AddEmployeePage(DriverFactory.getDriver());

        Allure.step("Click PIM > Add Employee");
        addPage.open();

        Allure.step("Assert the URL and the name fields");
        Assert.assertTrue(addPage.getCurrentUrl().contains("/pim/addEmployee"));
        Assert.assertTrue(addPage.isFirstNameDisplayed());
        Assert.assertTrue(addPage.isLastNameDisplayed());
    }

    @Test(description = "TC7: Add employee with an empty required field")
    public void testAddEmployeeWithoutFirstName() {
        loginAsAdmin();
        AddEmployeePage addPage = new AddEmployeePage(DriverFactory.getDriver());

        Allure.step("Open the Add Employee page");
        addPage.open();

        Allure.step("Enter the Last Name only and click Save");
        addPage.setLastNameField(DataProviders.getString("newEmployee", "lastName"));
        addPage.clickSaveButton();

        Allure.step("Assert 'Required' is shown under First Name");
        Assert.assertEquals(addPage.getFirstNameError(), "Required");
    }

    @Test(description = "TC8: End-to-end - add a new employee successfully")
    public void testAddEmployeeEndToEnd() {
        loginAsAdmin();
        String firstName = DataProviders.getString("newEmployee", "firstName");
        String lastName = DataProviders.getString("newEmployee", "lastName");
        AddEmployeePage addPage = new AddEmployeePage(DriverFactory.getDriver());

        Allure.step("Go to PIM > Add Employee");
        addPage.open();

        Allure.step("Enter the names and click Save");
        addPage.setFirstNameField(firstName);
        addPage.setLastNameField(lastName);
        addPage.clickSaveButton();

        Allure.step("Assert the Personal Details page opens");
        Assert.assertTrue(addPage.isPersonalDetailsOpened());

        Allure.step("Go back to Employee List and search for the employee");
        PimPage pimPage = new PimPage(DriverFactory.getDriver());
        pimPage.openEmployeeList();
        pimPage.searchEmployee(firstName);

        Allure.step("Assert the employee appears in the results");
        Assert.assertTrue(pimPage.resultsContain(firstName));
    }
}