package SeleniumFramework.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String ProductName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
//System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();

		// loginPage loginPage = new loginPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("aamir@selenium.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,\'mb-3\')]"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".col-lg-4 button:last-of-type")).click();

		// Explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/cart']")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName));
		// anyMatch will check if something matched and if yes will return a boolean
		// value as true
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// Actions a = new Actions(driver);
		// a.sendKeys(driver.findElement(By.cssSelector(" .user__address input")),
		// "Ind").build().perform();
		driver.findElement(By.cssSelector(" .user__address input")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results button")));
		driver.findElement(By.xpath("//section[contains(@class,'ta-results')]//button[2]")).click();

		// List <WebElement>
		// countriesList=driver.findElements(By.cssSelector(".ta-results button"));
		// countriesList.stream().filter(countryList->
		// countryList.getText().equalsIgnoreCase("India"));
		driver.findElement(By.cssSelector(".action__submit")).click();
		Boolean thanks = driver.findElement(By.cssSelector(".hero-primary")).getText()
				.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		Assert.assertTrue(thanks);
		driver.close();
	}

}
