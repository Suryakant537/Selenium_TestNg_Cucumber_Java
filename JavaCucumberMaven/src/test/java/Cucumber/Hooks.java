package Cucumber;


import Common.DriverInstance;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static Common.DriverInstance.driver;

public class Hooks {
    public WebDriver driver;

    @Before (order = 1)
    public void setUp() {
    }

    @AfterStep (order = 1)
    public void tearDown(Scenario scenario) {
        if(DriverInstance.appiumDriver!=null){
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverInstance.getAndroidDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        }
        else {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) DriverInstance.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        }
    }


}