package stepdefinitions.hooks;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Driver.DriverFactory;
import Utils.ConfigReader;
import Utils.ExtentManager;
import Utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    public static WebDriver driver;
    private static ExtentReports extent;
    public static ExtentTest test;

    @Before
    public void setUp(Scenario scenario) {
    	
    	
    	
        Properties prop = ConfigReader.loadConfig();
        driver = DriverFactory.initDriver(prop.getProperty("browser"));
        driver.get(prop.getProperty("baseUrl"));

        extent = ExtentManager.getExtent();
        test = extent.createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            String screenshotPath =
                ScreenshotUtil.captureScreenshot(driver, scenario.getName());
            test.fail("Scenario Failed")
                .addScreenCaptureFromPath(screenshotPath);
        } else {
            test.pass("Scenario Passed");
        }

        driver.quit();
        extent.flush();
    }
}
