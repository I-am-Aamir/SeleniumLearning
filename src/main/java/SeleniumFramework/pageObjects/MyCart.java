package SeleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFramework.Reusable.AbstractComponents;

public class MyCart extends AbstractComponents {

	WebDriver MyCartDriver;
	
	public MyCart(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.MyCartDriver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement buyNow;
	
	public List<WebElement> getCartProducts() {
		return cartProducts;
	}
	
	public Boolean matchProductByName(String ProductName) {
		Boolean match = getCartProducts().stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	public CheckoutPage clickBuyNow() {
		buyNow.click();
		CheckoutPage checkoutPage = new CheckoutPage(MyCartDriver);
		return checkoutPage;
	}
}
