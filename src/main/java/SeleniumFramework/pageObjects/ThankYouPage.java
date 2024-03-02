package SeleniumFramework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.Reusable.AbstractComponents;

public class ThankYouPage extends AbstractComponents {

	WebDriver thankYouPageDriver;
	By thanks = By.cssSelector(".hero-primary");
	public ThankYouPage(WebDriver driver) {
		super(driver);
		this.thankYouPageDriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean verifyThankYouMessage(String thankYouMessage) {
		Boolean thanksBoolean = thankYouPageDriver.findElement(thanks).getText().equalsIgnoreCase(thankYouMessage);
		return thanksBoolean;
	}
}
