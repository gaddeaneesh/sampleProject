package Sample_Group.Sample_Artifact;


import static org.testng.Assert.assertEquals;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners(utility.Listener.class)


public class FlipKartTesting {
	
	public static WebDriver driver;
	String parent;
	WebDriverWait wait;
	@BeforeSuite
	public void Initialization()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_jars\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	@BeforeTest
	public void openSite()
	{
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
	}
	@Test(priority=0)
	public void login() throws InterruptedException
	{
		
		By login = By.xpath("//a[text()='Log In']");
		clickButton(login);
		By phoneNumber = By.xpath("//div[contains(@class, '_39M2dM')]/input");
		enterText(phoneNumber, "9989885883");
		By password = By.xpath("//input[contains(@class, '_2zrpKA _3v41xv')]");
		enterText(password, "satish12$");
		By LoginButton = By.cssSelector("._2AkmmA._1LctnI._7UHT_c");
		clickButton(LoginButton);
		Thread.sleep(5000);
	}
	@Test(priority=1)
	public void search()
	{
		By Search = By.xpath("//div[@id='container']//input[@class='LM6RPg']");
		enterText(Search, "samsung mobiles");
		By searchButton = By.xpath("//div[@id='container']//button[@class='vh79eN']");
		clickButton(searchButton);
	}
	@Test(priority=2)
	public void selectItem() throws InterruptedException
	{
		wait= new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='_3wU53n']")));
		parent = driver.getWindowHandle();
		By selectItem = By.xpath("(//div[@class='_3wU53n'])[2]");
		clickButton(selectItem);
		Set<String> windows = driver.getWindowHandles();
		for(String window:windows)
		{
			if(window!=parent)
			{
				driver.switchTo().window(window);
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='container']//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")));
		By addToCart = By.xpath("//*[@id='container']//button[@class='_2AkmmA _2Npkh4 _2MWPVK']");
		clickButton(addToCart);
		driver.switchTo().window(parent);
		//Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@class='_1AHrFc _2k0gmP']"))).perform();
		By orders = By.xpath("//div[@id='container']//a[text()='Orders']");
		clickButton(orders);
	}
	@Test(priority=3)
	public void TestcaseToFail()
	{	
		String title=driver.getTitle();
        assertEquals(title, "InCorrect Title");
	}
	@AfterTest
	public void aftertest()
	{
		driver.quit();
	}
		
	
	public void enterText(By locater,String texttoenter)
	{
		driver.findElement(locater).sendKeys(texttoenter);
	}
	public void clickButton(By locater)
	{
		driver.findElement(locater).click();
	}
	

}
