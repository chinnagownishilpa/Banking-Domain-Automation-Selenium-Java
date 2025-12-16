package Driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
	private static WebDriver driver;
	
//	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//	public static void initDriver(String browser) {
//		if(browser.equalsIgnoreCase("chrome")) {
//			driver.set(new ChromeDriver());
//		}
//	}
//	
//	public static WebDriver getDriver() {
//		return driver.get();
//	}
//	
//	public static void quitDriver() {
//		driver.get().quit();
//		driver.remove();
//	}
//	
	
	public static WebDriver initDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	
	public static void quitDriver() {
		driver.quit();
	}
	
	
}
