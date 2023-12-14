package pavanacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pavanacademy.pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver InitializeDriver() throws IOException {
		// Properties class can read the global properties..
		// ChromeBrowser...
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//pavanacademy//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// FirefoxDriver
			System.setProperty("webdriver.gecko.driver", "/Users/spys/Documents/geckodriver");
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge Driver
			System.setProperty("webdriver.edge.driver", "/Users/spys/Documents/msedgedriver");

			// Initialize Edge driver
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launcApplication() throws IOException {
		driver = InitializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// String to HashMap- Jackson Datbind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	// Takes a screenshot using the provided WebDriver instance.
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		// Converts the WebDriver instance to TakesScreenshot interface.
		TakesScreenshot ts = (TakesScreenshot) driver;

		// Captures the screenshot and stores it in the 'source' File object.
		File source = ts.getScreenshotAs(OutputType.FILE);

		// Defines the destination file path for the screenshot.
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");

		// Copies the captured screenshot from 'source' to the destination 'file'.
		FileUtils.copyFile(source, file);

		// Returns the absolute path of the saved screenshot file.
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
