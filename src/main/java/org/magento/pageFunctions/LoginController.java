package main.java.org.magento.pageFunctions;


import main.java.org.magento.base.BaseSetupClass;
import main.java.org.magento.base.ReusableFunctions;
import main.java.org.magento.objectsRepository.HomePage;
import main.java.org.magento.objectsRepository.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

//All function related to LoginPage for maximum re-usability.
public class LoginController extends ReusableFunctions {

	Logger log = LogManager.getLogger(LoginController.class);
	LoginPage login = null;
	HomePage homePage= null;

	public LoginController(WebDriver driver) throws Exception	{

		super(driver); //  driver instance of ReusableFunctions class that all the page objects inherit from
			login = PageFactory.initElements(driver, LoginPage.class);
			homePage = PageFactory.initElements(driver, HomePage.class);
	}
	
	public LoginController launchURL() throws Exception{
		launchURL(BaseSetupClass.getURL());
		return this;
	}

	public LoginController clickOnSingInHeader() throws Exception	{

		clickObject(login.btnSignIn, "SingIn[Header] Button");
		return this;
	}

	public LoginController clickLoginButton() throws Exception	{

		clickObject(login.btnSubmit, "Login Button");
		return this;
	}

	public LoginController typeUserName(String email) throws Exception	{

		typeValue(login.userName, "userName", email);
		return this;
	}

	public LoginController typePassword(String password) throws Exception	{

		typeValue(login.enterPassword, "password", password);
		return this;
	}

	public LoginController waitForUserName(int intWaitTime) throws Exception	{
		waitForElement(waitCondition.tobePresent, login.userName);
		return this;
	}

	public LoginController waitForHomePageHeader(String userName) throws Exception {
		waitForElement(homePage.welcomeHeader);
		Thread.sleep(4000);
		String headerText= getText(homePage.welcomeHeader,"Welcome user..!");
			Assert.assertEquals(headerText,"Welcome, "+userName+"!","The homepage user welcome screen is shown as : "+headerText);
				log.info("The homepage user welcome screen is shown as : "+headerText);

		return this;
	}

	public LoginController clickSignIn() throws Exception	{
		clickObject(login.btnSubmit, "SignIn");
		return this;
	}

	public LoginController clickUsername() throws Exception	{
		clickObject(login.userName, "username clicked");
		return this;
	}

	public LoginController clickPassword() throws Exception	{
		clickObject(login.enterPassword, "password clicked");
		return this;
	}

	public void loginToApplication(String email, String password) throws Exception {

		launchURL()
			.clickOnSingInHeader()
				.clickUsername()
					.typeUserName(email)
				.clickPassword()
			.typePassword(password)
		.clickSignIn();

		log.info("Hey..! Login Successful....!");

	}

	public LoginController validateInvalidCredentialError() throws Exception {
		String errorMsg= getText(login.loginErrorMsg, "Invalid Credentials..!");
		Assert.assertEquals(errorMsg,"The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.",
				"The Invalid Credential error validation msg is coming incorrect as : "+errorMsg);
		log.info("The Invalid Credential error validation msg is coming as : "+errorMsg);

		return this;
	}



	
}
