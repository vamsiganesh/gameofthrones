package testClasses;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		"G:\\seleniumRelated\\MavenProject\\src\\test\\resources\\testResourcePackage\\Feature1w2sms.Feature" }, plugin = {
				"pretty", "html:target" }, monochrome = true)

public class MyRunner
{

}
