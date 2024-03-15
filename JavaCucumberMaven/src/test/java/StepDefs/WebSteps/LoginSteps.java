package StepDefs.WebSteps;

import Common.DriverInstance;
import Pages.WebPages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;

public class LoginSteps {

    LoginPage loginPage = PageFactory.initElements(DriverInstance.getWebDriver(), LoginPage.class);


    @Given("User is on Login page")
    public void theWebsiteSiteShouldBeLoaded() {
    }

    @And("User enters emailID {string}")
    public void userShouldBeAbleToEnterEmailID(String username) {

        loginPage.enterEmailID(username);
    }

    @And("User enters password {string}")
    public void userShouldBeAbleToEnterPassword(String password)
    {
        loginPage.enterPassword(password);
    }

    @And("User clicks on submit button")
    public void userClickOnSubmitButton() {

        loginPage.clickOnSubmitButton();
    }

    @Then("User should be logged-in successfully")
    public void userShouldLoggedInSuccessfully() {
        loginPage.verifyLoginPageForValidUser();
    }
    @Then("User should not logged in and proper message should be display")
    public void notLoggedinAndVerifyMessage()  {
        loginPage.verifyLoginPageForInvalidUser();
    }

}