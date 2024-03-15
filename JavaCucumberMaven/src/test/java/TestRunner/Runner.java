package TestRunner;

import Common.DriverInstance;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import static Common.DriverInstance.appiumDriver;
import static Common.DriverInstance.driver;

@CucumberOptions(
       // plugin = {"cucumberHooks.customReportListener","io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        plugin =    {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        features = {"src/resources"},
        glue =     {"StepDefs", "Cucumber"},
        tags =      "@Web"

)

public class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    //@BeforeClass
    public void init(){
      //  if (driver == null) DriverInstance.getWebDriver();
    }

    @AfterClass
    public void teardown(){
        if (driver != null){
        DriverInstance.getWebDriver().quit();}
        if(appiumDriver!=null){
        DriverInstance.getAndroidDriver().quit();}
    }
}