package pavanacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import pavanacademy.TestComponents.BaseTest;
import pavanacademy.pageObjects.CartPage;
import pavanacademy.pageObjects.ProductCatalogue;


public class ErrorValidation extends BaseTest{

@Test(groups= {"ErrorHandling"},retryAnalyzer=pavanacademy.TestComponents.Retry.class)
public void LogInValidation() throws IOException, InterruptedException

{
		ProductCatalogue productCatalogue = landingPage.loginPage("spk.yadavalli@gmail.com", "AZaz09$");
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
	}

@Test(groups = {"ErrorHandling"})
public void ProductNameValidation() throws IOException, InterruptedException

{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginPage("spky@gmail.com", "AZaz09$$");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
}
}
