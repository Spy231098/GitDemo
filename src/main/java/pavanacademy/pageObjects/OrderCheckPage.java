package pavanacademy.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pavanacademy.AbsctractComponents.AbstractComponent;

public class OrderCheckPage extends AbstractComponent{
	
	public WebDriver driver;
	
	public OrderCheckPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//tr[@class=\"ng-star-inserted\"]/td[2]")
	List<WebElement> OrderedItemsName;
	
	public Boolean ItemCheck(String productName)
	{
		Boolean NameMatch = OrderedItemsName.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return NameMatch;
	}

}
