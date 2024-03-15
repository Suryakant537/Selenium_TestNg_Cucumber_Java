package Pages.WebPages;


import Common.WebUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;


public class EmployeeInfoPage extends WebUtils{




    @FindBy(xpath = "//label[contains(text(),'Employee Id')]/..//following-sibling::div//input")
    public WebElement employeeIDTextbox;

    @FindBy(css = "input[name='firstName']")
    public WebElement firstNameTextbox;


    @FindBy(css = "input[name='lastName']")
    public WebElement lastNameTextbox;

    @FindBy(css = "button[type='submit']")
    public WebElement SaveButton;


    @FindBy(xpath = "//input[@name='middleName']")
    public WebElement middlename;






    public void clickOnSaveButton() {
        Reporter.log("Click on Save Button");
        waitForElementVisibility(SaveButton, 50);
        click(SaveButton);
    }

    public void enterLastName() throws InterruptedException {
        Reporter.log("Enter Text on lastName Textbox ");
       String lastname =  generateFakeName();
        waitForElementVisibility(lastNameTextbox, 50);
        clearTextBox(lastNameTextbox);
        enterText(lastNameTextbox, lastname);
    }

    public void clearMiddlename() {
        Reporter.log("Clear Middle Name Text Box");
        waitForElementVisibility(middlename,50);
        clearSuperTextBox(middlename);
    }

    public String enterFirstname() throws InterruptedException {
        Reporter.log("Enter Text on firstName Textbox ");
        String actuaEmployeeName = generateFakeName();
        waitForElementVisibility(firstNameTextbox, 50);
        Thread.sleep(5000);
        clearSuperTextBox(firstNameTextbox);
        enterText(firstNameTextbox, actuaEmployeeName);
        return actuaEmployeeName;
    }
    public String enterEmployeeID() {
        Reporter.log("Enter Text on Employee ID Textbox ");
        String empId = generateFakeNumber();
        waitForElementVisibility(employeeIDTextbox, 50);
        clearSuperTextBox(employeeIDTextbox);
        enterText(employeeIDTextbox, empId);
        return empId;
    }


}
