package SeleniumFramework.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.Reusable.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	WebDriver checkoutPageDriver;
	By inputCountry = By.cssSelector(".user__address input");
	By secondCountry = By.xpath("//section[contains(@class,'ta-results')]//button[2]");
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.checkoutPageDriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void optSecondCountry(String country) {
		Actions a = new Actions(checkoutPageDriver);
		a.sendKeys(checkoutPageDriver.findElement(inputCountry),country).build().perform();
		waitForElementToAppear(secondCountry);
		checkoutPageDriver.findElement(secondCountry).click();
	}
	
	public ThankYouPage clickOnPlaceOrder() {
		placeOrder.click();
		ThankYouPage thankYouPage = new ThankYouPage(checkoutPageDriver);
		return thankYouPage;
	}
	
	//adding this here to learn on GIT
}
