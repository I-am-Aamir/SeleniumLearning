package SeleniumFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.Reusable.AbstractComponents;

public class LoginPage extends AbstractComponents{

	public WebDriver loginPageDriver;
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.loginPageDriver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = logindriver.findElement(By.id("userEmail"));
	//PageFactory design pattern
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement incorrectUsernameOrPassword;
	
	public ProductCalatague loginDetails(String email, String pwd) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(pwd);
		login.click();
		ProductCalatague productCalatague = new ProductCalatague(loginPageDriver);
		return productCalatague;
	}
	
	public void goTo() {
		loginPageDriver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String incorrectUsernameOrPassword() {
		waitForWebElementToAppear(incorrectUsernameOrPassword);
		return incorrectUsernameOrPassword.getText();
	}
}
