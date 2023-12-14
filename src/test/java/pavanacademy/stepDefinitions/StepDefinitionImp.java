 package pavanacademy.stepDefinitions;

import java.io.IOException;

import io.cucumber.java.en.Given;
import pavanacademy.TestComponents.BaseTest;
public class StepDefinitionImp extends BaseTest{
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		launcApplication();
	}
	

}
