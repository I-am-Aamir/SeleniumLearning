package SeleniumFramework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumFramework.Reusable.OrdersPage;
import SeleniumFramework.TestComponent.BaseTest;
import SeleniumFramework.pageObjects.CheckoutPage;
import SeleniumFramework.pageObjects.MyCart;
import SeleniumFramework.pageObjects.ProductCalatague;
import SeleniumFramework.pageObjects.ThankYouPage;

public class FrameworkBuilding extends BaseTest{

	String ProductName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = {"Purchases","ErrorHandeling"}, retryAnalyzer=SeleniumFramework.TestComponent.Retry.class)
	//thier is one more way to not have retryAnalyzer mentioned in each and every test rather use listerners for it using IAnnotationTransformer
//	public void orderSubmit(String Username, String Password, String ProductName) throws IOException {
	public void orderSubmit(HashMap<String, String> input) {
		String country = "Ind";
		String thankYouMessage = "THANKYOU FOR THE ORDER.";

//		ProductCalatague productCalatague = loginPage.loginDetails(Username, Password);
		System.out.println(input.get("UserName"));
		ProductCalatague productCalatague = loginPage.loginDetails(input.get("UserName"), input.get("Password"));
		productCalatague.getProductsList();
//		productCalatague.getProductByName(ProductName);
		productCalatague.getProductByName(input.get("ProductName"));
//		productCalatague.addProductToCart(ProductName);
		productCalatague.addProductToCart(input.get("ProductName"));
		// inherited from abstract component parent class
		MyCart myCart = productCalatague.clickOnCart();
		myCart.getCartProducts();
//		Boolean match = myCart.matchProductByName(ProductName);
		Boolean match = myCart.matchProductByName(input.get("ProductName"));
		// validations cannot go to page object model, has to be under test case only
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = myCart.clickBuyNow();
		checkoutPage.optSecondCountry(country);
		ThankYouPage thankYouPage = checkoutPage.clickOnPlaceOrder();
		Boolean thanksBoolean = thankYouPage.verifyThankYouMessage(thankYouMessage);
		Assert.assertTrue(thanksBoolean);
	}

	@Test(dependsOnMethods = {"orderSubmit"})
	public void orderHistory() {
		ProductCalatague productCalatague = loginPage.loginDetails("aamir@selenium.com", "Selenium@123");
		OrdersPage orderPage = productCalatague.clickOnOrders();
		Assert.assertTrue(orderPage.matchOrderByName(ProductName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
//		Object[][] data = new Object[2][3];
//		data[0][0]="aamir@selenium.com";
//		data[0][1]="Selenium@123";
//		data[0][2]="ADIDAS ORIGINAL";
//		data[1][0]="aamir@sz.com";
//		data[1][1]="@Sz12345";
//		data[1][2]="IPHONE 13 PRO";
		// return data;

//		return new Object[][] {{"aamir@selenium.com","Selenium@123","ADIDAS ORIGINAL"},{"aamir@sz.com","@Sz12345","IPHONE 13 PRO"}};

//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("UserName", "aamir@selenium.com");
//		map1.put("Password", "Selenium@123");
//		map1.put("ProductName", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map2 = new HashMap<String,String>();
//		map2.put("UserName", "aamir@sz.com");
//		map2.put("Password", "@Sz12345");
//		map2.put("ProductName", "IPHONE 13 PRO");
//		
//		return new Object[][] {{map1},{map2}};

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\SeleniumFramework\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}