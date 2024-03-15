package Pages.WebPages;


import Common.WebUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;


public class PIMPage extends WebUtils {
    public WebUtils webUtils = new WebUtils();

    @FindBy(xpath = "//label[contains(text(),'Employee Id')]/..//following-sibling::div//input")
    public WebElement employeeIDTextbox;


    @FindBy(xpath = "//label[contains(text(),'Employee Name')]/..//following-sibling::div//input")
    public WebElement firstNameTextbox;

    @FindBy(xpath = "div[class='oxd-table-cell-actions']")
    public WebElement recordCheckBox;



    @FindBy(css = "button[type='submit']")
    public WebElement searchButton;


    @FindBy(xpath = "//div[@class='oxd-table-card']//div[3]")
    public WebElement getFirstEmployeeName;


    @FindBy(xpath = "//div[@class='oxd-table-card']//div[2]")
    public WebElement getFirstEmployeeID;

    public void clickOnSearchButton() {
        Reporter.log("Click on Search Button");
        waitForElementVisibility(searchButton,50);
        webUtils.click(searchButton);
    }

    public void waitForRecord() {
        Reporter.log("Wait For Record To load");
        waitForElementVisibility(recordCheckBox,50);
        webUtils.click(recordCheckBox);
    }

    public void enterEmployeeFirstname(String firstName) {
        Reporter.log("Enter Text on firstName Textbox ");
        waitForElementVisibility(searchButton,50);
        webUtils.enterText(firstNameTextbox, firstName);
    }
    public void enterEmployeeID(String employeeID) {
        Reporter.log("Enter Text on Employee ID Textbox ");
        waitForElementVisibility(searchButton,50);
        webUtils.enterText(employeeIDTextbox, employeeID);
    }

    public String getEmployeeEmployeeID() {
        Reporter.log("Get Text of Employee ID");
        waitForElementVisibility(getFirstEmployeeID,50);
        String employeeID = webUtils.getText(getFirstEmployeeID);
        return employeeID;
    }

    public String getEmployeeName() {
        Reporter.log("Get Text of Employee FirstName");
        waitForElementVisibility(getFirstEmployeeName,50);
        String employeeName = webUtils.getText(getFirstEmployeeName);
        return employeeName;
    }



}
