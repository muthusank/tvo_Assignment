package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DocsAndSeriesPage {

	private WebDriver driver;
	
	public DocsAndSeriesPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href='/series-docs']")
	private WebElement lnk_docsSeries;
	@FindBy(xpath = "//span[text()='All']")
	private WebElement lnk_all;
	@FindBy(xpath = "//div[@class='series-docs-nav__nav-button series-docs-nav__next-button']")
	private WebElement btn_next;
	@FindBy(xpath = "//div[@class='series-docs-nav__nav-button series-docs-nav__prev-button']")
	private WebElement btn_previous;
	@FindBy(xpath = "//span[text()='Docs']")
	private WebElement lnk_category;
	@FindBy(xpath = "//a[@href='/video/documentaries/1001-nights-apart']")
	private WebElement lnk_video;
	//@FindBy(xpath = "//a[@aria-label='Watch Impossible Engineering']")
	//WebElement btn_playvideo;
	@FindBy(xpath = "(//span[contains(@class,'MuiListItemText-primary')])[7]")
	private WebElement Clk_Searchicon;
	@FindBy(xpath = "//input[@title='search field']")
	private WebElement Clk_Searchfield;
	
	public void navigateToDocs() 
	{
		lnk_docsSeries.click();
		lnk_all.click();
	}

	public boolean allDisplayed() 
	{
		boolean b=false;
		try
		{
			b=lnk_all.isDisplayed();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
		
		//boolean status = lnk_all.isDisplayed();
		//return status;
	}

	public void click_Next_Previous_Arrow() throws InterruptedException 
	{
		btn_next.click();
		Thread.sleep(1000);
		btn_previous.click();
		Thread.sleep(1000);
	}

	public boolean categorydisplayed()
	{
		boolean category = lnk_category.isDisplayed();
		return category;
	}

	public void clickedToOpenvideo() throws InterruptedException 
	{
		lnk_category.click();
		Thread.sleep(5000);
		lnk_video.click();
		Thread.sleep(5000);
	}

	/*public boolean watchNowDisplayed() 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(btn_watchnow));
		boolean status = btn_watchnow.isDisplayed();
		return status;
	}*/
	
	public boolean ClickSearchIcon() 
	{
		Clk_Searchicon.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(Clk_Searchicon));
		boolean status = Clk_Searchfield.isDisplayed();
		return status;	
	}
	
	public void EnterSearchCriteria(String Keywordinput)throws InterruptedException 
	{
		Clk_Searchfield.click();
		Actions actions = new Actions(driver);
		actions.sendKeys(Keywordinput);
		Thread.sleep(2000);
		actions.sendKeys(Keys.RETURN).perform();
		Thread.sleep(3000);
	}
}
