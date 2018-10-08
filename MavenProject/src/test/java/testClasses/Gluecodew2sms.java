package testClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageClasses.HomePage;
import PageClasses.SendSmsPage;
import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class Gluecodew2sms {

	public WebDriver driver;
	public HomePage hp;
	public SendSmsPage ssp;
	public WebDriverWait wait;
	Scenario s;
	Properties pro;

	@Before
	public void method(Scenario s) throws Exception {
		// Use Scenario object for current scenario
		this.s = s;
		pro = new Properties();
		FileInputStream fip = new FileInputStream(
				"G:\\seleniumRelated\\MavenProject\\src\\test\\resources\\testResourcePackage\\PropertiesBrowser.properties");
		pro.load(fip);
	}

	@Given("^launch the \"(.*)\" browser$")
	public void method1(String b) throws Exception {
		if (b.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", pro.getProperty("chromedriver"));
			driver = new ChromeDriver();
		} else if (b.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", pro.getProperty("firefoxdriver"));
			driver = new FirefoxDriver();
		} else if (b.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.chrome.driver", pro.getProperty("iedriver"));
		}

		else {

			System.setProperty("webdriver.chrome.driver", pro.getProperty("chromedriver"));
			driver = new ChromeDriver();
		}
		driver.get(pro.getProperty("url"));
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 20);
		wait.until(
				temp -> (((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete"));
		// create object for page classes
		hp = new HomePage(driver);
		ssp = new SendSmsPage(driver);

	}

	@Then("^title test validation \"(.*)\"$")
	public void method2(String x) {
		String tit = driver.getTitle();
		if (tit.contains(x))

		{
			s.write("title test passed");
		} else {
			byte[] ssbytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbytes, "title test failed");
			Assert.fail();

		}
	}

	@And("^close site$")
	public void method3() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}

	@Then("^enter the mobilenumber as \"(.*)\"$")
	public void method4(String m) throws Exception {

		wait.until(ExpectedConditions.visibilityOf(hp.mbno));
		hp.mbno.sendKeys(m);
		Thread.sleep(5000);

	}

	@Then("^fill password as \"(.*)\"$")
	public void method5(String p) throws InterruptedException {
		wait.until(temp -> (JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
		hp.pswd.sendKeys(p);

		wait.until(ExpectedConditions.visibilityOf(hp.login));
		hp.click();
		Thread.sleep(3000);
	}

	@Then("^validate output for criteria \"(.*)\"$")
	public void method7(String z) throws Exception {

		try {
			Thread.sleep(2000);
			wait.until(temp -> (JavascriptExecutor) driver).executeScript("return document.readyState")
					.equals("complete");

			if (z.equals("all_valid") && ssp.tomobile.isDisplayed()) {
				s.write("vaild login test passed");
			} else if (z.equals("mbno_blank") && hp.mbno_blank_error.isDisplayed()) {
				s.write("blank mobile number test passed");
			} else if (z.equals("blank_pswd") && hp.pswd_blank_error.isDisplayed()) {
				s.write("blank password test passed");

			} else if (z.equals("mbno_invalid") && hp.mbno_invalid.isDisplayed()) {

				s.write("inavali mobile test passed ");
			}

			else if (z.equals("pswd_invalid") && hp.pswd_invalid.isDisplayed())

			{
				s.write("invalid password test passed");
			} else {
				byte[] ssbytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				s.embed(ssbytes, "Error in log in validation ");

			}
		} catch (Exception ex) {
			byte[] ssbytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			s.embed(ssbytes, "Error in log in validation ");

		}
	}

	@When("^login with valid data$")
	public void method8(DataTable dt) {
		List<List<String>> l = dt.asLists(String.class);

		hp.mbno.sendKeys(l.get(1).get(0));
		hp.mbno.sendKeys(l.get(1).get(1));
	}

	@Then("^sendsmspage will be displayed$")
	public void method9() {
		if (ssp.tomobile.isDisplayed()) {
			s.write("Login test successfull");
		}
	}

	@Then("^dologout$")
	public void method10() {

		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(ssp.logout));
		ssp.click();
	}

	@Then("^homepage will be reopened$")
	public void method11() {
		wait.until(ExpectedConditions.visibilityOf(hp.mbno));
		if (hp.mbno.isDisplayed()) {
			s.write("homepage displayed after logout succesfull");
		}
	}
}
