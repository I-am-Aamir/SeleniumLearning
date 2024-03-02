package SeleniumFramework.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver; // defining publically so that the driver we define within method has life
								// outside as well by referring to this driver
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		// properties
		Properties prop = new Properties();
		FileInputStream GlobalFile = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\SeleniumFramework\\Resources\\GlobalData.properties");
		prop.load(GlobalFile);
		//java ternary operator
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser"): prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();//for running in headless mode
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")){
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			if (browserName.contains("headless")) {
				driver.manage().window().setSize(new Dimension(1440,900)); //setting full screen for backend
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		//FileUtils is available in commons.io dependancy
		//json to string
		//readfiletostring is deprecated hence we need to pass value of encoding format we are trying to write the string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		//jackson databind for string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List <HashMap<String,String>> map = mapper.readValue(jsonContent, new TypeReference <List<HashMap<String,String>>>(){});
		return map;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		//casting the driver
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screenshot = ts.getScreenshotAs(OutputType.FILE);
		String destFileLocation = System.getProperty("user.dir")+ "//reports//screenshots//" + testCaseName + ".png";
		File destFile = new File(destFileLocation);
		FileUtils.copyFile(screenshot, destFile);
		return destFileLocation;
		
	}

	@BeforeMethod(alwaysRun = true)//since for grouping these are ignores as they don't contain the group name yet is a must prerequisite 
	public LoginPage LandingPage() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)//since for grouping these are ignores as they don't contain the group name yet is a must prerequisite 
	public void closeBrowser() {
		driver.close();
	}
}
