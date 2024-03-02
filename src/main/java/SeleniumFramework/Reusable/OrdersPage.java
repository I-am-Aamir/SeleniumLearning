package SeleniumFramework.Reusable;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends AbstractComponents{

	WebDriver orderPageDriver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.orderPageDriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr[class='ng-star-inserted'] td:nth-child(3)")
	List<WebElement> ordersList;
	
	public boolean matchOrderByName(String productName) {
		boolean match = ordersList.stream().anyMatch(order -> order.getText().equalsIgnoreCase(productName));
		return match;
	}
}
