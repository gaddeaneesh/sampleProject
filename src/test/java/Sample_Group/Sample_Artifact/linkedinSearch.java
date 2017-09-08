package Sample_Group.Sample_Artifact;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class linkedinSearch {
	WebDriver driver=null;
	WebDriverWait wait;
	
	
	LinkedIn_Objects HomePage;
	@BeforeSuite
	public void Initialization()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium_jars\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	@BeforeTest
	public void openSite()
	{
		driver.get("https://www.linkedin.com/");
		driver.manage().window().maximize();
	}
	
	@Test(priority=0)
	public void login() throws InterruptedException, IOException
	{
		HomePage = PageFactory.initElements(driver,LinkedIn_Objects.class);
		File file=new File("D:\\workspace\\Sample_Artifact\\input.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		inputStream.close();
		XSSFSheet sheet= wb.getSheetAt(0);
		String userName = sheet.getRow(1).getCell(0).getStringCellValue();
		String password = sheet.getRow(1).getCell(1).getStringCellValue();
		String searchText = sheet.getRow(1).getCell(2).getStringCellValue();	
		ExcelWriting exe = new ExcelWriting(driver);
		HomePage.eleUserName.sendKeys(userName);
		HomePage.elePassword.sendKeys(password);
		HomePage.eleLogin.click();
		wait= new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[starts-with(@id,'a11y-ember')]")));
		HomePage.eleSearch.sendKeys(searchText);
		HomePage.eleSearchbtn.click();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
		Thread.sleep(2000);
		exe.createExcel();
		int pagecount=driver.findElements(By.xpath("//ol[starts-with(@id,'ember')]/li[@class='page-list']//li")).size();
		int rowcount=1;
		rowcount= exe.EmployeeData(rowcount,HomePage.contact_Names,HomePage.contact_Links);
		for(int i=2;i<=pagecount;i++)
		{	
			WebElement pagenation = driver.findElement(By.xpath("//ol[starts-with(@id,'ember')]/li[@class='page-list']//li["+i+"]"));
			pagenation.click();
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
			Thread.sleep(2000);
			rowcount =exe.EmployeeData(rowcount,HomePage.contact_Names,HomePage.contact_Links);	
		}
			   
		exe.excelClose();
		wb.close();
	}
	@AfterTest
	public void aftertest()
	{
		driver.quit();
		
	}
	

}
