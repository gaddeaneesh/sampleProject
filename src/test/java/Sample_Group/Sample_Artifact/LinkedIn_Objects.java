package Sample_Group.Sample_Artifact;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LinkedIn_Objects {
	
	@FindBy(xpath="//input[@id='login-email']")
	WebElement eleUserName;
	@FindBy(xpath="//input[@id='login-password']")
	WebElement elePassword;
	@FindBy(xpath="//input[@id='login-submit']")
	WebElement eleLogin;
	@FindBy(xpath="//input[starts-with(@id,'a11y-ember')]")
	WebElement eleSearch;
	@FindBy(xpath="//div[@id='nav-search-controls-wormhole']/button")
	WebElement eleSearchbtn;
	@FindBy(xpath="//h3[starts-with(@id,'ember')]/span[1]")
	List<WebElement> contact_Names;	
	@FindBy(xpath="//h3[starts-with(@id,'ember')]/span[1]//ancestor::a")
	List<WebElement> contact_Links;
	
	
}
