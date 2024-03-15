package StepDefs.WebSteps;

import Common.DriverInstance;
import Common.WebUtils;
import Cucumber.Hooks;
import Pages.WebPages.EmployeeInfoPage;
import Pages.WebPages.LoginPage;
import Pages.WebPages.PIMPage;
import Pages.WebPages.SidePanelPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EmployeeSteps {
    LoginPage loginPage = PageFactory.initElements(DriverInstance.getWebDriver(), LoginPage.class);
    SidePanelPage sidePanelPage = PageFactory.initElements(DriverInstance.getWebDriver(), SidePanelPage.class);

    PIMPage pimPage = PageFactory.initElements(DriverInstance.getWebDriver(), PIMPage.class);

    EmployeeInfoPage employeeInfoPage = PageFactory.initElements(DriverInstance.getWebDriver(), EmployeeInfoPage.class);
    String actualEmployeeName,actualEmployeeID;

    @Given("user should be logged into the OrangeHRM website")
    public void userShouldBeLoggedIntoTheOrangeHRMWebsite() {
        loginPage.enterEmailID("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickOnSubmitButton();
    }


    @And("user navigate to the View Employee List page")
    public void userNavigateToTheViewEmployeeListPage() {
        loginPage.enterEmailID("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickOnSubmitButton();
        loginPage.clickOnInfoTab();
    }

    @When("user enter the employee detail")
    public void userEnterTheEmployeeDetail() throws InterruptedException {
        actualEmployeeName = employeeInfoPage.enterFirstname();
        employeeInfoPage.enterLastName();
        employeeInfoPage.clearMiddlename();
        actualEmployeeID = employeeInfoPage.enterEmployeeID();
    }

    @And("user click on the Save button")
    public void userClickOnTheSaveButton() {
        employeeInfoPage.clickOnSaveButton();
    }

    @And("user navigate to the PIM page")
    public void userNavigateToThePIMPage() {
        sidePanelPage.clickOnPIMTab();
    }

    @Then("the employee details should be displayed on the View Employee List page")
    public void theEmployeeDetailsShouldBeDisplayedOnTheViewEmployeeListPage() throws InterruptedException {
       pimPage.enterEmployeeFirstname(actualEmployeeName);
       pimPage.enterEmployeeID(actualEmployeeID);
       pimPage.clickOnSearchButton();
        String expectedEmployeeName = pimPage.getEmployeeName();
        String expectedEmployeeID = pimPage.getEmployeeEmployeeID();
        Assert.assertEquals(actualEmployeeName,expectedEmployeeName);
        Assert.assertEquals(actualEmployeeID,expectedEmployeeID);
    }
}
