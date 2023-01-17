package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.DocsAndSeriesPage;
import testbase.Baseclass;

public class TestNGTests2 {
	WebDriverWait wait;

	public static WebDriver driver;
	public DocsAndSeriesPage dp;

	@BeforeTest
	public void setUp() throws IOException 
	{
		driver = Baseclass.setup();
		dp = new DocsAndSeriesPage(driver);
	}

	@Test(priority=1, description= "Navigate to All Docs&Series")
	public void NavigatetoDocs()
	{
		dp.navigateToDocs();
		Assert.assertTrue(dp.allDisplayed());
	}

	@Test(priority=2, description= "Check whether the Previous and Next Buttons are Clickable")
	public void verifyPagination() throws InterruptedException
	{
		dp.click_Next_Previous_Arrow();
		Assert.assertTrue(dp.categorydisplayed());
	}

	@Test(priority=3, description= "Navigate to Docs and Launch a Video URL")
	public void verifyVideoLinkLaunchable() throws InterruptedException
	{
		dp.clickedToOpenvideo();
		//Assert.assertTrue(dp.watchNowDisplayed());
	}
	
	@Test(priority=4, description= "Click on Search Icon and Perform Video Search with a Keyword", dataProvider = "Searchkeyword")
	public void PerformSearch(String Keywordinput) throws InterruptedException
	{
		dp.ClickSearchIcon();
		Assert.assertTrue(dp.ClickSearchIcon());
		dp.EnterSearchCriteria(Keywordinput);
	}
	
	@AfterTest
	public void tearDown() 
	{
		if (driver != null)
			driver.quit();
	}
	
	@DataProvider(name="Searchkeyword")
	public static Object[][] Keywords()
	{
	 return new Object[][] {{"1001 Nights Apart | TVO Today"},{"19 Days | TVO Today"},{"Beautiful Scars | TVO Today"}};
	}

}
