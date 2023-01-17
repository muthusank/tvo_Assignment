package testbase;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Baseclass 
{
	public static 	WebDriver driver;
	public static String browser;
	public static Properties prop;
	public static  WebDriver setup() throws IOException
	{
		WebDriverWait wait;
		String path="./src/main/java/config/config.properties";
		FileInputStream fis =new FileInputStream(new File(path));
		prop= new Properties();
		prop.load(fis);
		browser=prop.getProperty("browser");
		
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",prop.getProperty("firefoxDriver"));
			driver= new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		
		else if(browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", prop.getProperty("edgeDriver"));
			driver= new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		
		else if(browser.equalsIgnoreCase("chrome"))
			{
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriver"));
			driver= new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			}
		
		else
		{
			Throwable thr = new Throwable();
			thr.initCause(null);
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get(prop.getProperty("url"));
		Assert.assertEquals("TVO Today | Current Affairs Journalism, Documentaries and Podcasts", driver.getTitle());
		return driver;	
	}
}

