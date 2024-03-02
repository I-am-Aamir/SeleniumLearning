package SeleniumFramework.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumFramework.Reusable.AbstractComponents;

public class ProductCalatague extends AbstractComponents {

	WebDriver ProductCalatagueDriver;
	
	public ProductCalatague(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.ProductCalatagueDriver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".col-lg-4 button:last-of-type");
	By productAddedToCart = By.cssSelector("#toast-container");
	By addToCartLoader = By.cssSelector(".ng-animating");
	
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	public List<WebElement> getProductsList() {
		waitForElementToAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		//page factory cannot be applied within webelement.findelement
		waitForElementToAppear(productAddedToCart);
		waitForElementToDisappear(addToCartLoader);
	}
	
}
