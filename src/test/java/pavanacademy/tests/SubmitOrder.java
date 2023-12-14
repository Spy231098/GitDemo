package pavanacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pavanacademy.TestComponents.BaseTest;
import pavanacademy.pageObjects.CartPage;
import pavanacademy.pageObjects.CheckoutPage;
import pavanacademy.pageObjects.ConfirmationPage;
import pavanacademy.pageObjects.OrderCheckPage;
import pavanacademy.pageObjects.ProductCatalogue;

public class SubmitOrder extends BaseTest {
	

	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException

	{
		String productName = input.get("productName");
		ProductCatalogue productCatalogue = landingPage.loginPage(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();

		// .box .ng-star-inserted .em-spacer-1

	}

	@Test(dependsOnMethods={"submitOrder"},groups = {"OrderHandling"},dataProvider="getData")
	public void OrderCheck(HashMap<String,String> input) throws InterruptedException {
		String productName = input.get("productName");
		ProductCatalogue productCatalogue = landingPage.loginPage(input.get("email"),input.get("password"));
		OrderCheckPage orderCheckPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderCheckPage.ItemCheck(productName));
		driver.findElement(By.xpath("//i[@class='fa fa-sign-out']")).click();


	}
	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//pavanacademy//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
	}
	
//	
	
//	public List<HashMap<String, String>> getData() throws IOException
//	{
////		HashMap<String,String> map = new HashMap<String,String>();
////		map.put("email", "spk.yadavalli@gmail.com");
////		map.put("password", "AZaz09$$");
////		map.put("productName", "ZARA COAT 3");
////		
////		HashMap<String,String> map1 = new HashMap<String,String>();
////		map1.put("email", "spky@gmail.com");
////		map1.put("password", "AZaz09$$");
////		map1.put("productName", "ADIDAS ORIGINAL");
//		//List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//pavanacademy//data//PurcahseOrder.json");
//		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//pavanacademy//data//PurchaseOrder.json");
//
//		return data;
//		//return new Object[][] {{"spk.yadavalli@gmail.com","AZaz09$$","ZARA COAT 3"},{"spky@gmail.com","AZaz09$$","ADIDAS ORIGINAL"}};
//	}

}
