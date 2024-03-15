package Pages.WebPages;

import Common.WebUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
public class SidePanelPage extends  WebUtils{



    @FindBy(xpath = "//a[contains(@href,'viewMyDetails')]")
    public WebElement infoTab;

    @FindBy(xpath = "//a[contains(@href,'Pim')]")
    public WebElement pimTab;


    public void clickOnInfoTab() {
        Reporter.log("Click on Info Tab");
        waitForElementVisibility(infoTab,50);
        click(infoTab);
    }


    public void clickOnPIMTab() {
        Reporter.log("Click on PIM Tab");
        waitForElementVisibility(pimTab,50);
        click(pimTab);
    }
}
