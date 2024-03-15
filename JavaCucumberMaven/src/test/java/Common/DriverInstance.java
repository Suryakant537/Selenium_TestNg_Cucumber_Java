package Common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverInstance {
    public static WebDriver driver;
    public static AppiumDriver appiumDriver;

    public static void initDriver() throws MalformedURLException {
        Configuration config = new Configuration();
        JSONObject properties = config.readJSOnFile("config");
        String applicationURL = (String) properties.get("APP_URL");
        String platformName = System.getProperty("tags");
//        String platformName = "@Web";
        switch (platformName) {
            case "@Web":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
                try {
                    driver = new ChromeDriver(chromeOptions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                driver.get(applicationURL);
                driver.manage().window().maximize();
                break;
            case "@Web-Firefox":
                System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
                driver = new FirefoxDriver();
                driver.get(applicationURL);
                driver.manage().window().maximize();
                break;
            case "@Web-Edge":
                System.setProperty("webdriver.edge.driver", driverPath + " msedgedriver.exe");
                driver = new EdgeDriver();
                driver.get(applicationURL);
                driver.manage().window().maximize();
                break;
            case "@Android":
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel4");
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");

                caps.setCapability("appPackage", "com.google.android.calculator");
                caps.setCapability("appActivity", "com.android.calculator2.Calculator");
                appiumDriver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name provided!");
        }
    }

    static String driverPath = System.getProperty("user.dir") + "\\src\\Webdrivers\\";

    public static WebDriver getWebDriver() {
        if (driver == null) {
            try {
                initDriver();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;
    }

    public static AppiumDriver getAndroidDriver() {
        if (appiumDriver == null) {
            try {
                initDriver();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return appiumDriver;
    }

}
