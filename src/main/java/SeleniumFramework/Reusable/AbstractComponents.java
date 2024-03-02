package SeleniumFramework.Reusable;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.pageObjects.MyCart;

public class AbstractComponents {
	

	
		
	 WebDriver AbstractComponentsDriver;
	public AbstractComponents(WebDriver driver) {
		this.AbstractComponentsDriver=driver;
		
	}
	
	By cartButton = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orders;
	
	
	public void waitForElementToAppear(By findBy) {
		// Explicit wait
		WebDriverWait wait = new WebDriverWait(AbstractComponentsDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(By findBy) {
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(AbstractComponentsDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
		
	public void waitForElementToBeClickable(By FindBy) {
		//Explicit wait
		WebDriverWait wait = new WebDriverWait(AbstractComponentsDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(FindBy));
	}
	
	public void waitForWebElementToAppear(WebElement webelement) {
		WebDriverWait wait = new WebDriverWait(AbstractComponentsDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webelement));
	}
	
	public MyCart clickOnCart() {
		waitForElementToBeClickable(cartButton);
		AbstractComponentsDriver.findElement(cartButton).click();
		MyCart myCart = new MyCart(AbstractComponentsDriver);
		return myCart;
	}
	
	public OrdersPage clickOnOrders() {
		//waitForWebElementToAppear(orders);
		orders.click();
		OrdersPage ordersPage = new OrdersPage(AbstractComponentsDriver);
		return ordersPage;
	}
	
}
