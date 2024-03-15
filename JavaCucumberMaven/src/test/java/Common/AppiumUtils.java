package Common;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static Common.DriverInstance.appiumDriver;
import static Common.DriverInstance.getAndroidDriver;
import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;


/**
 * This class is responsible for performing all required user actions to
 * automate a web application. It is generally made for web applications that
 * run on desktop(e.g Windows/Mac etc.) browsers. Some methods might work for
 * mobile devices also but complete support is not present.
 *
 *  @author Pranjal
 *
 */
public class AppiumUtils {
	private final Logger LOGGER = LoggerFactory.getLogger(AppiumUtils.class);

	static WebDriverWait wait = new WebDriverWait(getAndroidDriver(), Duration.ofSeconds(TIMEOUT));


	/**
	 * Wait for element to be visible
	 *
	 * @param element
	 */
	public static void waitForVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * Wait for element to be visible
	 *
	 * @param element
	 */
	public static void waitForVisible(WebElement element, int timeout) {
		new WebDriverWait(appiumDriver, Duration.ofSeconds(TIMEOUT)).ignoring(RuntimeException.class)
				.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait for element to be enabled
	 *
	 * @param element
	 */
	public static void waitForEnable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * Wait for element to be invisible
	 *
	 * @param element
	 */
	public static void waitForNotVisible(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public WebElement findElement(String locator) {
		String[] arrayLocator = locator.split(";");
		String type = arrayLocator[0];
		String locatorValue = arrayLocator[1];

		LOGGER.info("Finding locator by " + type + " and locator value is " + locatorValue);

		WebElement element = null;
		if (type.equalsIgnoreCase("id")) {
			element = appiumDriver.findElement(AppiumBy.id(locatorValue));
		} else if (type.equalsIgnoreCase("xpath")) {
			element = appiumDriver.findElement(AppiumBy.xpath(locatorValue));
		} else if (type.equalsIgnoreCase("resourceId")) {
			element = appiumDriver.findElement(AppiumBy.id(locatorValue));
		}

		return element;
	}
	/*
	 * Tap / Click on element
	 *
	 * @param : element : clickable element.
	 */
	public void click(WebElement element) {
		WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(TIMEOUT));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
}
