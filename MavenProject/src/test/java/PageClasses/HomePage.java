package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// All locators in HomePage
	@FindBy(name = "mobileNo")
	public WebElement mbno;

	@FindBy(name = "password")
	public WebElement pswd;

	@FindBy(xpath = "(//button[contains(text(),'Login')])[1]")
	public WebElement login;

	@FindBy(xpath = "//b[text()='Enter your mobile number']")
	public WebElement mbno_blank_error;

	@FindBy(xpath = "//b[text()='Enter password'][@id='mainErr']")
	public WebElement pswd_blank_error;

	@FindBy(xpath = "//b[text()='Your mobile number is not register with us.']")
	public WebElement mbno_invalid;

	@FindBy(xpath = "//b[text()='Incorrect number or password! Try Again.']")
	public WebElement pswd_invalid;

	@FindBy(xpath = "//*[text()='INDIA’S #1 SMS PLATFORM']")
	public WebElement pageHeading;

	@FindBy(xpath = "//*[contains(text(),'click here')]")
	public WebElement problemLogin;

	// operations

	public void fillmbno(String m) {
		mbno.sendKeys(m);
	}

	public void pswdfill(String p) {
		pswd.sendKeys(p);
	}

	public void click() {
		login.submit();
	}

}
