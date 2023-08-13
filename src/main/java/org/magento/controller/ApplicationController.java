package main.java.org.magento.controller;

import main.java.org.magento.base.BaseSetupClass;
import main.java.org.magento.pageFunctions.CreateAccountController;
import main.java.org.magento.pageFunctions.HomeController;
import main.java.org.magento.pageFunctions.LoginController;
import main.java.org.magento.pageFunctions.ShoppingCartController;


import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class ApplicationController{

	public  WebDriver driver= (new BaseSetupClass().getDriver());

	//====================CONTROLLER OBJECTS=======================

	public HashMap <String, Object> getData;
	public LoginController login = null;
	public CreateAccountController createAccount = null;
	public HomeController homeController = null;
	public ShoppingCartController shoppingCartController=null;
	public TestScenarioDataController testScenarioData =null;
	public ApplicationController(WebDriver driver)	{
		this.driver = driver;
	}

	public LoginController LoginController() throws Exception	{
		if(login == null)
		{
			login = new LoginController(driver);
		}
		return login;
	}

	public CreateAccountController CreateAccountController () throws Exception	{
		if(createAccount == null)
		{
			createAccount = new CreateAccountController(driver);
		}
		return createAccount;
	}

	public HomeController HomeController () throws Exception	{
		if(homeController == null)
		{
			homeController = new HomeController(driver);
		}
		return homeController;
	}

	public ShoppingCartController ShoppingCartController () throws Exception	{
		if(shoppingCartController == null)
		{
			shoppingCartController = new ShoppingCartController(driver);
		}
		return shoppingCartController;
	}

	public TestScenarioDataController TestScenarioData() throws Exception {
		
		if (testScenarioData==null) 
		{
			testScenarioData= new TestScenarioDataController();
		}
		return testScenarioData;
	}
	
	
	
}