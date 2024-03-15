package Common;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import static Common.DriverInstance.driver;


/**
 * This class is responsible for performing all required user actions to
 * automate a web application. It is generally made for web applications that
 * run on desktop(e.g Windows/Mac etc.) browsers. Some methods might work for
 * mobile devices also but complete support is not present.
 * 
 *  @author Pranjal
 *
 */
public class WebUtils {
	private final Logger LOGGER = LoggerFactory.getLogger(WebUtils.class);
	//private WebDriver driver = null;

//	public WebUtils(WebDriver driver) {
//		this.driver = driver;
//	}

	/**
	 * It will refresh the current browser tab.
	 */
	public void refreshBrowser() {
		LOGGER.info("Refreshing the browser...");
		driver.navigate().refresh();
	}
	private static final Properties PROPERTIES = new Properties();
	/**
	 * It will switch to the frame element.
	 * 
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		LOGGER.info("Switching to iframe {}", element);
		driver.switchTo().frame(element);
	}

	public void switchToDefaultFrame() {
		LOGGER.info("Switching to default content.");
		driver.switchTo().defaultContent();
	}

	/**
	 * It will refresh the current browser tab by getting its current URL.
	 */
	public void refreshWithCurrentUrl() {
		LOGGER.info("Refreshing the browser...");
		driver.get(driver.getCurrentUrl());
	}

	/**
	 * It will refresh the current browser by hitting F5 key.
	 */
	public void refreshWithF5Key() {
		LOGGER.info("Refreshing the browser...");
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
	}

	/**
	 * It will open a new blank tab in browser.
	 * 
	 */
	public void openNewTab() {
		((JavascriptExecutor) driver).executeScript("window.open('about:blank','_blank');");
	}

	/**
	 * It will close all other tabs except the provided one.
	 * 
	 * @param originalHandle you can get it by using driver.getWindowHandle();
	 */
	public void closeOtherTabs(String originalHandle) {
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
	}


	/**
	 * It will close the next tab opened other then original tab.
	 */
	public void closeNextTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}

	/**
	 * It will return the current URL of the page.
	 * 
	 * @return Current URL
	 */
	public String getCurrentURL() {

		return driver.getCurrentUrl();
	}

	/**
	 * It will switch to the 2nd tab present.
	 */
	public void switchToNextTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
	}

	/**
	 * It will switch to 1st tab.
	 */
	public void switchToPreviousTab() {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(0));
	}

	/**
	 * It will get element.
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(String locator) {
		LOGGER.info("Getting element for :-- [{}]", locator);
		WebElement element = null;
		try {
			element = driver.findElement(getByObject(locator));
		} catch (Exception e) {
			LOGGER.error("!!!!!! Exception Occurred : {}: ", e.getMessage());
		}

		return element;
	}

	/**
	 * It will get list of elements of a specific locator.
	 * 
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> getElements(String locator) {
		LOGGER.info("Getting elements for :-- [{}]", locator);
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(getByObject(locator));
		} catch (Exception e) {
			LOGGER.error( "!!!!!! Exception Occurred : {}: ", e.getMessage());
		}

		return elements;
	}

	/**
	 * It will return the element using its parent element.
	 * 
	 * @param parentElement
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(WebElement parentElement, String locator) {
		LOGGER.info( "Getting element for :-- [{}]", locator);
		WebElement element = null;
		try {
			element = parentElement.findElement(getByObject(locator));
		} catch (Exception e) {
			LOGGER.error( "!!!!!! Exception Occurred : {}: ", e.getMessage());
		}

		return element;
	}

	/**
	 * It will return list of elements using parent element.
	 * 
	 * @param parentElement
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> getElements(WebElement parentElement, String locator) {
		LOGGER.info( "Getting elements for :-- [{}]", locator);
		List<WebElement> elements = null;
		try {
			elements = parentElement.findElements(getByObject(locator));
		} catch (Exception e) {
			LOGGER.error("!!!!!! Exception Occurred : {}: ", e.getMessage());
		}

		return elements;
	}

	/**
	 * For WebElement : It will return only visible element (neglecting hidden
	 * elements of same locator).
	 * 
	 * @param locator
	 * @param timeOut
	 * @return WebElement
	 */
	public WebElement getDisplayedWebElement(String locator, long timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(org.openqa.selenium.NoSuchElementException.class)
				.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				LOGGER.info("Polling for element untill displayed for :-- [{}]", locator);
				List<WebElement> elements = driver.findElements(getByObject(locator));
				return elements.stream().filter(element -> element.isDisplayed()).findFirst().get();
			}
		});

		return element;
	}

	/**
	 * For WebElement : It will return only visible elements (neglecting hidden
	 * elements of same locator)
	 * 
	 * @param locator
	 * @param timeOut
	 * @return List<WebElement>
	 */
	public List<WebElement> getDisplayedWebElements(String locator, long timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(org.openqa.selenium.NoSuchElementException.class)
				.ignoring(NoSuchElementException.class);
		List<WebElement> elements = (List<WebElement>) wait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				LOGGER.info("Polling for element untill displayed for :-- [{}]", locator);
				List<WebElement> elements = driver.findElements(getByObject(locator));
				return elements.stream().filter(element -> element.isDisplayed()).collect(Collectors.toList());
			}

		});

		return elements;
	};

	/**
	 * It will give the object of "By"
	 * 
	 * @param locator e.g : //div[@class='demo']_xpath
	 * @return By object
	 */
	public By getByObject(String locator) {
		locator = locator.trim();
		By byObj = null;
		if (locator.endsWith("_xpath")) {
			byObj = By.xpath(locator.replaceAll("_xpath", ""));
		} else if (locator.endsWith("_css")) {
			byObj = By.cssSelector(locator.replaceAll("_css", ""));
		} else if (locator.endsWith("_id")) {
			byObj = By.id(locator.replaceAll("_id", ""));
		} else if (locator.endsWith("_linkText")) {
			byObj = By.linkText(locator.replaceAll("__linkText", ""));
		} else if (locator.endsWith("_name")) {
			byObj = By.name(locator.replaceAll("_name", ""));
		}

		return byObj;

	}

	/**
	 * It just check that an element is present on the DOM of a page.
	 * 
	 * @param locator
	 * @param seconds
	 */
	public void waitForElementPresence(String locator, long seconds) {
		LOGGER.info("waiting for presence of element [{}] for {} seconds", locator, seconds);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(getByObject(locator)));
	}

	/**
	 * It just check that an element is present on the DOM of a page.
	 *
	 * @param locator
	 */
	public static WebElement waitForElementPresence(WebDriver driver, By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	/**
	 * It will give the property value present in properties file.
	 *
	 * @param propertyName
	 * @return property value
	 */
	public static String getProperty(String propertyName) {

		return PROPERTIES.getProperty(propertyName);
	}

	/**
	 * It will check that an element is present on the DOM of a page and visible.
	 * 
	 * @param locator
	 * @param seconds
	 */
	public void waitForElementVisibility(String locator, long seconds) {
		LOGGER.info("waiting for visibility of element [{}] for {} seconds", locator, seconds);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(getByObject(locator)));

	}

	/**
	 * It will check that an element is present on the DOM of a page and visible.
	 * 
	 * @param
	 * @param seconds
	 */
	public void waitForElementVisibility(WebElement webElement, long seconds) {
		LOGGER.info( "waiting for visibility of element [{}] for {} seconds", webElement, seconds);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(webElement));

	}

	/**
	 * It will check that an element is present on the DOM of a page and visible.
	 *
	 * @param locator
	 */
	public static WebElement waitForElementVisibilityWithMaxTimeOut(WebDriver driver, By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	/**
	 * It will hard wait for the given seconds.
	 * 
	 * @param seconds
	 */
	public void sleep(int seconds) {
		LOGGER.info("Waiting for {} seconds", seconds);
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			LOGGER.info("Exception occurred while waiting for {} seconds ", seconds);
		}

	}

	/**
	 * It will click on a given locator.
	 * 
	 * @param
	 */
	public void click(WebElement element) {
		try {
			LOGGER.info("Clicking on : [{}]", element);
			//highlightWebElement(element);
			element.click();
		} catch (Exception e) {
			LOGGER.error("Exception occurred while clicking : [{}]", e.getMessage());
		}
	}

	/**
	 * It will navigate to the specified URL.
	 * 
	 * @param URL
	 */
	public void navigateToURL(String URL) {
		LOGGER.info("Navigating to URL : [{}]", URL);
		driver.navigate().to(URL);

	}

	/**
	 * It will give the page title.
	 * 
	 * @return
	 */
	public String getPageTitle() {
		String pageTitle = driver.getTitle();
		LOGGER.info("Page Title is : [{}]", pageTitle);

		return pageTitle;

	}

	/**
	 * It will give the text present in element.
	 * 
	 * @return element's text value
	 */
	public String getText(WebElement element) {
		//highlightWebElement(element);
		String elementText = element.getText();
		LOGGER.info("Text found for : [{}] is : [{}]", element, elementText);

		return elementText;

	}

	/**
	 * It will get an element's text using any attribute. For e.g attribute can be :
	 * innerText,textContent,title etc as defined in the element html code
	 * 
	 * @param
	 * @param attribute
	 * @return
	 */
	public String getTextUsingAttribute(WebElement element, String attribute) {
		//highlightWebElement(element);
		String elementText = element.getAttribute(attribute);
		LOGGER.info("Text found for : [{}] is : [{}]", element, elementText);

		return elementText;

	}

	/**
	 * It will get InnerText of element using JavaScript.
	 * 
	 * @param element
	 * @return innerText of element.
	 */
	public String getInnerText(WebElement element) {
		LOGGER.info("Getting innerText using javascript, WebElement : [{}]", element);
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", element);
	}

	/**
	 * It will get textContent of element using JavaScript.
	 * 
	 * @param element
	 * @return textContent of element.
	 */
	public String getTextContent(WebElement element) {
		LOGGER.info("Getting textContent using javascript, WebElement : [{}]", element);
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", element);
	}

	public String getAttributeUsingJs(WebElement element, String attribute) {
		LOGGER.info("Getting [{}] attribute using javascript for locator [{}] :", attribute, element);
		return (String) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].getAttribute('" + attribute + "');", element);
	}

	/**
	 * It enters the value in text box.
	 * 
	 * @param
	 * @param text
	 */
	public void enterText(WebElement element, String text) {
		//highlightWebElement(element);
		//element.clear(); // clearing if any text is present in text box.
		LOGGER.info("Entering text for element: [{}] Text is :[{}]", element, text);
		element.sendKeys(text);

	}



	/**
	 * It will clear the text present in text box.
	 * 
	 * @param
	 */

	public void clearTextBox(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
	}

	public void clearSuperTextBox(WebElement element) {
		element.sendKeys(Keys.BACK_SPACE);
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
	}

	public String generateFakeName(){
     Faker faker = new Faker();
	 String fakeName = faker.name().firstName();
		return fakeName;
	}

	public String generateFakeNumber(){
		Faker faker = new Faker();
		String fakeName = faker.number().digits(5);
		return fakeName;
	}

	/**
	 * It enters the value in text box.
	 * 
	 * @param
	 * @param text
	 */
	public void enterTextUsingActions(WebElement element, String text) {
		//highlightWebElement(element);
		LOGGER.info("Entering text for : [{}] ::   Text is : [{}]", element, text);
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(text);
		actions.build().perform();
	}

	/**
	 * It focuses on given web element.
	 * 
	 * @param
	 * @param element
	 */
	public void jsFocus(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].focus();", element);
	}

	/**
	 * It clicks on given web element using javascript.
	 * 
	 * @param element
	 */
	public void jsClick(WebElement element) {
		LOGGER.info("Clicking on element : {} using javascript", element);
		//highlightWebElement(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * It scrolls to the given WebElement.
	 * 
	 * @param
	 * @param element
	 * @return WebElement
	 */
	public WebElement scrollingToElementofAPage(WebElement element) {
		LOGGER.info("Scrolling to element : {} ", element);
		//highlightWebElement(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

		return element;
	}
	
	/**
	 * It scrolls to the given WebElement and click on it using javascript executor.
	 */
	public void scrollToElementAndClickUsingJS(WebElement element) {
		LOGGER.info("Scrolling to element : {} ", element);
		//highlightWebElement(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		jsClick(element);
	}
	

	public void scrollTillEndOfPage() {
		LOGGER.info("Scrolling till end of the page");
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * It will highlight the web element
	 * 
	 * @param element
	 */
//	public void highlightWebElement(WebElement element) {
//		((JavascriptExecutor) driver).executeScript(
//				"arguments[0].setAttribute('style', 'background:#ffffb3; border:3px solid green;');", element);
//	}

	/**
	 * This method copies the content to system Clipboard and than paste it. i.e it
	 * just performs copy paste operation like (Ctrl C + Ctrl V)
	 * 
	 * @param element
	 * @param stringToBePasted
	 */
	public void copyPaste(WebElement element, String stringToBePasted) {
		LOGGER.info("Clearing any text if present..");
		element.clear();
		element.click();
		LOGGER.info("copy pasting : [{}] .", stringToBePasted);
		StringSelection stringSelection = new StringSelection(stringToBePasted);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			LOGGER.error("!!!!!!!! Exception occured while copy pasting the given content....: {}",
					e.getMessage());
		}

	}

	/**
	 * It will click on element using Actions class.
	 * 
	 * @param element
	 * @param seconds
	 */
	public void actionClick(WebElement element, long seconds) {
		//highlightWebElement(element);
		LOGGER.info("Clicking on WebElement using Actions class, element : [{}]", element);
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		driverWait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
	}


	/**
	 * It will click on element using Actions class.
	 * 
	 * @param element WebElement
	 */
	public void actionClick(WebElement element) {
		//highlightWebElement(element);
		LOGGER.info("Clicking on WebElement using Actions class, element : [{}]", element);
		//WebDriverWait driverWait = new WebDriverWait(driver, Long.valueOf(getProperty("maxTimeOut")));
		WebDriverWait driverWait = new WebDriverWait(driver,Duration.ofSeconds(50));
		driverWait.until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
	}

	/**
	 * It will click on element without waiting for it to be clickable using Actions
	 * class.
	 * 
	 * @param element WebElement
	 */
	public void actionPress(WebElement element) {
		//highlightWebElement(element);
		LOGGER.info("Clicking on WebElement using Actions class, element : [{}]", element);
		Actions actions = new Actions(driver);
		actions.click(element).build().perform();
	}

	/**
	 * It will select the value from dropdown using value attribute present in HTML
	 * tag.
	 * 
	 * @param element
	 * @param value
	 */
	public void selectDropDownByValue(WebElement element, String value) {
		LOGGER.info("Selecting value [{}] from dropdown, element : [{}]", value, element);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * It will select the dropdown value using its index.
	 * 
	 * @param element
	 * @param
	 */
	public void selectDropDownByIndex(WebElement element, int index) {
		LOGGER.info("Selecting index [{}] from dropdown, element : [{}]", index, element);
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * It will select the value using current text visible on dropdown.
	 * 
	 * @param element
	 * @param
	 */
	public void selectDropDownByVisibleText(WebElement element, String visibleText) {
		LOGGER.info("Selecting visibleText [{}] from dropdown, element : [{}]", visibleText, element);
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	/**
	 * It will check if element is enabled or not.
	 * 
	 * @param element
	 * @return enabled status
	 */
	public boolean isElementEnabled(WebElement element) {
		LOGGER.info("Checking if element is enabled, element : [{}]", element);

		return element.isEnabled();
	}

	/**
	 * It will check if element is selected or not.
	 * 
	 * @param element
	 * @return enabled status
	 */
	public boolean isElementSelected(WebElement element) {
		LOGGER.info("Checking if element is selected, element : [{}]", element);

		return element.isSelected();
	}

	/**
	 * It will check if element is selected or not.
	 * 
	 * @param element
	 * @return enabled status
	 */
	public boolean isElementDisplayed(WebElement element) {
		LOGGER.info("Checking if element is displayed, element : [{}]", element);

		return element.isDisplayed();
	}

	/**
	 * It will perform mouse hover operation on the WebElement.
	 * 
	 * @param elementToHover
	 */
	public void mouseHover(WebElement elementToHover) {
		LOGGER.info("Mouse hovering on element , element : [{}]", elementToHover);
		Actions hover = new Actions(driver);
		hover.moveToElement(elementToHover);
		hover.build();
		hover.perform();
	}

	/**
	 * It will perform mouse hover operation on the WebElement using javascript.
	 * 
	 * @param elementToHover
	 */
	public void mouseHoverUsingJs(WebElement elementToHover) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);}else if(document.createEventObject){ arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) driver).executeScript(mouseOverScript, elementToHover);
	}


	/**
	 * It will return the text of only parent element excluding the text present in
	 * child elements. e.g : Sometimes we have to get the text content of a parent
	 * element like <div> and exclude all other tags and their contents which may be
	 * present in it. like <div>Hello
	 * <h1>ChildText</h1></div> , so this method will return "Hello" only.
	 * 
	 * @param element
	 * @return parent element text content.
	 */
	public String getOnlyParentElementText(WebElement element) {
		String text = element.getText();
		for (WebElement child : element.findElements(By.xpath("./*"))) {
			text = text.replaceFirst(child.getText(), "");
		}

		return text;
	}

	/**
	 * It will generate random number based on given range. e.g : if range is 10,
	 * then it will return values from 0-9 inclusive
	 * 
	 * @param range
	 * @return
	 */
	public int generateRandomNumber(int range) {
		Random rand = new Random();
		return rand.nextInt(range);
	}



	/**
	 * It will wait until javascript is fully loaded inpage.
	 * 
	 */
	public void waitForJavascriptLoading(long seconds) {
		LOGGER.info("Waiting for javascript to load for {} seconds", seconds);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * It will get the first occurrence of the particular text and click it e.g.
	 * under any locator - get the value of the attribute and find its first
	 * occurrence and click it.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param attributeName
	 * @param text
	 * 
	 */
	public void clickFirstDisplayedElement(String locator, long timeOut, String attributeName, String text) {
		List<WebElement> options = this.getDisplayedWebElements(locator, timeOut);
		WebElement OptionToSelect = options.stream().filter(option -> option.getAttribute(attributeName).contains(text))
				.findFirst().get();
		this.click(OptionToSelect);
	}


	/**
	 * It will get selected value from dropdown
	 * 
	 * @param element
	 */
	public String getSelectedValueInDropDown(WebElement element) {
		Select select = new Select(element);
		String selectedValueFromDropdown = select.getFirstSelectedOption().getText();
		LOGGER.info("Selected value from dropdown [{}] , is [{}]", element, selectedValueFromDropdown);
		return selectedValueFromDropdown;
	}


	/**
	 * It will re attempt the click if StaleElementReferenceException exception
	 * occurs.
	 * 
	 * @param
	 * @return
	 */
	public boolean retryingClick(WebElement element) {
		LOGGER.info("Inside retryingFindClick method");
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				actionClick(element);
				result = true;
				break;
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {
			}
			attempts++;
		}
		return result;
	}
	
	
	public boolean clickUntilElementIsInvisible(List<WebElement> elements) {
		LOGGER.info("Inside retryingFindClick method");
		boolean result = false;
		int attempts = 0;
		while (elements.size() > 0 && attempts <= 4) {
			try {
				actionClick(elements.get(0));
				result = true;
				break;
			} catch (StaleElementReferenceException | ElementNotInteractableException e) {
			}
			attempts++;
		}
		return result;
	}

	
	 /** It will perform drag drop operation using Action class.
     * @param driver
     * @param sourceElement
     * @param targetElement
     */
    public void dragDropUsingActions(WebDriver driver, WebElement sourceElement, WebElement targetElement) {
    //	highlightWebElement(sourceElement);
	//	highlightWebElement(targetElement);
    	(new Actions(driver)).dragAndDrop(sourceElement, targetElement).perform();
    }
    
    /** It will drag and drop the element by first holding the source element for the given time and then will drag to target element.
     * @param driver
     * @param sourceElement
     * @param targetElement
     * @param timeToHoldInSeconds
     */
    public void dragDropUsingClickAndHold(WebDriver driver, WebElement sourceElement, WebElement targetElement, long timeToHoldInSeconds) {
    	//highlightWebElement(sourceElement);
		//highlightWebElement(targetElement);
    	Actions actions = new Actions(driver);
		actions.clickAndHold(sourceElement).pause(Duration.ofSeconds(timeToHoldInSeconds)).moveToElement(targetElement).release().build().perform();
    }

    public void pressEnter() {    	
    	Robot robot;
		try {
			robot = new Robot();
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		    robot.delay(200);
		} catch (AWTException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void setValueAttribute(WebElement element, String attValue) {
    	LOGGER.info("Setting value to to {}", attValue);
    	String script = "arguments[0].value=" + "'" + attValue + "'";
    	System.out.println("YES IT IS  :" + script);
		((JavascriptExecutor) driver).executeScript("arguments[0].value=" + "'" + attValue + "'", element);
    }

    /**	
     *  It wil accept the alert by first waiting for it
     * @param wait in seconds
     * @return Alert object
     */
	public Alert acceptAlert(long wait) {
		WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(wait));
		try {
			synchronized (waitTime) {
				Alert alert = driver.switchTo().alert();
				alert.accept();

				return alert;
			}

		} catch (NoAlertPresentException ex) {
			return null;
		}

	}
}
