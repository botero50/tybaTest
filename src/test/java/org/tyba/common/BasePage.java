package org.tyba.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public static WebDriver driver;
	
	@BeforeClass(alwaysRun = true)
	public static WebDriver initializeDriver() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(publicData.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		getUrl(publicData.ENVIRONMENT_URL);
		return driver;
	}
	
	@AfterSuite(alwaysRun = true)
    public void tearDown(ITestContext  context) {
    	driver.quit();
    }
	
	public static void getUrl(String sUrl)
	{
		driver.get(sUrl);
	}
}