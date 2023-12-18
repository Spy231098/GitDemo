package pavanacademy.tests;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pavanacademy.pageObjects.LandingPage;

public class StandAlone2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("spk.yadavalli@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("AZaz09$$");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until((ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3"))));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*=cart]")).click();
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class=\"cartSection\"]/h3"));
		Boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "India").build().perform();
		//.ta-item
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,\"ta-item\")])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		String orderID = driver.findElement(By.cssSelector(".box .ng-star-inserted .em-spacer-1")).getText();
		System.out.println(orderID.split(" ")[1].split(" ")[0]);
		System.out.println("Its is Updated Project");
		System.out.println("This is updated...!");
		driver.close();		
				//.box .ng-star-inserted .em-spacer-1
		
		
		
	}

}
