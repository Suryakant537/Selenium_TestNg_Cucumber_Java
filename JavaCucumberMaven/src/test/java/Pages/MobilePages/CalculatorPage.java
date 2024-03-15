package Pages.MobilePages;

import Common.AppiumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorPage extends AppiumUtils {


    @FindBy(id = "com.google.android.calculator:id/digit_0")
    public WebElement zeroNumber;
    @FindBy(id = "com.google.android.calculator:id/digit_1")
    public WebElement oneNumber;
    @FindBy(id = "com.google.android.calculator:id/digit_2")
    public WebElement twoNumber;
    @FindBy(id = "com.google.android.calculator:id/digit_3")
    public WebElement threeNumber;
    @FindBy(id = "com.google.android.calculator:id/digit_4")
    public WebElement fourNumber;
    @FindBy(id = "com.google.android.calculator:id/digit_5")
    public WebElement fiveNumber;
    @FindBy(id = "com.google.android.calculator:id/digit_6")
    public WebElement sixNumber;

    @FindBy(id = "com.google.android.calculator:id/digit_7")
    public WebElement sevenNumber;
    @FindBy(id = "com.google.android.calculator:id/digit_8")
    public WebElement eightNumber;
    @FindBy(id = "com.google.android.calculator:id/digit_9")
    public WebElement nineNumber;
    @FindBy(id = "com.google.android.calculator:id/op_add")
    public WebElement plusOperation;

    @FindBy(id = "com.google.android.calculator:id/op_sub")
    public WebElement minusOperation;

    @FindBy(id = "com.google.android.calculator:id/op_mul")
    public WebElement multiplicationOperation;

    @FindBy(id = "com.google.android.calculator:id/op_div")
    public WebElement divisionOperation;

    @FindBy(id = "com.google.android.calculator:id/eq")
    public WebElement equalOperation;
    @FindBy(id = "com.google.android.calculator:id/result_final")
    public WebElement resultnumber;

    public void enterNumber(String number) {
        switch (number) {
            case "0":
                zeroNumber.click();
                break;
            case "1":
                oneNumber.click();
                break;
            case "2":
                twoNumber.click();
                break;
            case "3":
                threeNumber.click();
                break;
            case "4":
                fourNumber.click();
                break;
            case "5":
                fiveNumber.click();
                break;
            case "6":
                sixNumber.click();
                break;
            case "7":
                sevenNumber.click();
                break;
            case "8":
                eightNumber.click();
                break;
            case "9":
                nineNumber.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid number: " + number);
        }
    }

}
