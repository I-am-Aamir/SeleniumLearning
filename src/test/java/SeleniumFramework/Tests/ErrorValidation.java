package SeleniumFramework.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework.TestComponent.BaseTest;

public class ErrorValidation extends BaseTest {
	
	@Test(groups= {"ErrorHandeling"})
	public void LoginErrorValidation() {
		loginPage.loginDetails("aamir@selenium.com", "12345");
		//Assert.assertEquals("Incorrect email or password.", loginPage.incorrectUsernameOrPassword());
		Assert.assertEquals("Incorrect email password.", loginPage.incorrectUsernameOrPassword());// removed or to fail
		
		
	}



	
	
}
