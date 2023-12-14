
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  
  Background:
  Given I landed on Ecommerce Page
  
  
  
  @tag2
  Scenario Outline: Positive Yest of Submitting the order
    Given Logged in the username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  											| 	password	 		|	productName			|
      | spk.yadavalli@gmail.com 		|  		AZaz09$$		|	ZARA COAT 3			|
      | spky@gmail.com							|    	AZaz09$$		|	ADDIDAS ORIGINAL|
