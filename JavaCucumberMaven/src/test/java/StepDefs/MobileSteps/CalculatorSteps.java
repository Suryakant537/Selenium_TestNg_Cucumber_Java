package StepDefs.MobileSteps;

import Common.AppiumUtils;
import Common.DriverInstance;
import Pages.MobilePages.CalculatorPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CalculatorSteps extends AppiumUtils {
    CalculatorPage calculatorPage = PageFactory.initElements(DriverInstance.getAndroidDriver(), CalculatorPage.class);


    @Then("the result should be {string}")
    public void theResultShouldBe(String arg0) {
        Assert.assertEquals(arg0, calculatorPage.resultnumber.getText());
    }


    @Given("User opened the calculator app")
    public void userOpenedTheCalculatorApp() {
        DriverInstance driverInstance = new DriverInstance();
    }

    @And("User press the plus button")
    public void userPressThePlusButton() {
        calculatorPage.plusOperation.click();
    }

    @And("User press equal button")
    public void userPressEqualButton() {
        calculatorPage.equalOperation.click();
    }

    @When("User enters first number {string}")
    public void userEntersFirstNumber(String number) {
        calculatorPage.enterNumber(number);
    }

    @And("User enter Second number {string}")
    public void userEnterSecondNumber(String number) {
        calculatorPage.enterNumber(number);
    }

    @Then("User gets the result {string}")
    public void userGetsTheResult(String result) {
        Assert.assertEquals(result, calculatorPage.resultnumber.getText());
    }

    @And("User press the subtraction button")
    public void userPressTheSubtractionButton() {
        calculatorPage.minusOperation.click();

    }

    @And("User press the multiplication button")
    public void userPressTheMultiplicationButton() {
        calculatorPage.multiplicationOperation.click();
    }

    @And("User press the division button")
    public void userPressTheDivisionButton() {
        calculatorPage.divisionOperation.click();
    }
}
