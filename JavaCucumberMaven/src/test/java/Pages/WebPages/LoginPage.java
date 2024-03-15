package Pages.WebPages;


import Common.DriverInstance;
import Common.WebUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LoginPage extends WebUtils {

    @FindBy(css = "input[name='username']")
    public WebElement emailIDTextbox;

    @FindBy(css = "div[role='alert'] p ")
    public WebElement errorMessage;

    @FindBy(css = "input[name='password']")
    public WebElement passwordTextbox;

    @FindBy(css = "button[type='submit']")
    public WebElement SubmitButton;

    @FindBy(css = "li[class='oxd-userdropdown'] i")
    public WebElement logoutDropdown;

    @FindBy(css = "a[href='/web/index.php/auth/logout']")
    public WebElement logoutButton;


    public void clickOnSubmitButton() {
        Reporter.log("Click on Submit Button");
        waitForElementVisibility(SubmitButton, 50000);
        click(SubmitButton);
    }

    public void clickOnErrorMessage() {
        Reporter.log("Click on Submit Button");
        waitForElementVisibility(errorMessage, 50000);
        click(SubmitButton);
    }

    public void enterEmailID(String email) {
        Reporter.log("Enter Text on email Textbox");
        waitForElementVisibility(emailIDTextbox, 50000);
        enterText(emailIDTextbox, email);
    }

    public void clickOnlogout() {
        Reporter.log("Logout from site");
        waitForElementVisibility(logoutDropdown, 50000);
        click(logoutDropdown);
        waitForElementVisibility(logoutButton, 50000);
        click(logoutButton);
    }

    public void enterPassword(String password) {
        Reporter.log("Enter Text on password  Textbox ");
        waitForElementVisibility(passwordTextbox, 50000);
        enterText(passwordTextbox, password);
    }

    public void verifyLoginPageForValidUser() {
        waitForElementVisibility(logoutDropdown, 30);
        String currentURL = DriverInstance.getWebDriver().getCurrentUrl();
        String actualURLPart = "dashboard/index";
        currentURL.contains(actualURLPart);
        this.clickOnlogout();
        waitForElementVisibility(SubmitButton, 60);

    }
    @FindBy(xpath = "//a[contains(@href,'viewMyDetails')]")
    public WebElement infoTab;

    public void clickOnInfoTab() {
        Reporter.log("Click on Info Tab");
        waitForElementVisibility(infoTab,50);
        click(infoTab);
    }

    public void verifyLoginPageForInvalidUser() {
        waitForElementVisibility(passwordTextbox, 30);
        this.errorMessage.isDisplayed();

    }
}
