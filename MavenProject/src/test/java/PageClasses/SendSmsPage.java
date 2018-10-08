package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.And;

public class SendSmsPage

{

	// TypeCasting Driver Object
	WebDriver driver;

	public SendSmsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Locators of SendSmsPage

	@FindBy(name = "toMobile")
	public WebElement tomobile;

	@FindBy(name = "message")
	public WebElement typemsg;

	@FindBy(xpath = "//*[@class='logout']")
	public WebElement logout;

	public void toMobileFill(String tm) {
		tomobile.sendKeys(tm);
	}

	public void typemsgfill(String tmg) {
		typemsg.sendKeys(tmg);
	}

	public void click() {
		logout.click();
	}
}