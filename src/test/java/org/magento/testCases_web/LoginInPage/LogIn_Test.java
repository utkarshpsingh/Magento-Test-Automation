package test.java.org.magento.testCases_web.LoginInPage;

import main.java.org.magento.base.BaseSetupClass;
import main.java.org.magento.base.ReusableFunctions;
import main.java.org.magento.pageFunctions.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ConcurrentHashMap;

@Test
public class LogIn_Test extends BaseSetupClass {

	static Logger log = LogManager.getLogger(LogIn_Test.class);
	public ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> getDataScript= null;
	public WebDriver driver= null;
	public ReusableFunctions functions= null;
	LoginController loginPage;

	@BeforeMethod(alwaysRun= true)
	public void init() throws Exception {

		getDataScript= getTestData();
		driver= getDriver();
		functions= new ReusableFunctions(driver);
		loginPage =App().LoginController();
	}


	@Test(description = "Login Test Case",groups = {"SanityTest"})
	public void signInTest() throws Exception {
				
		String testCaseName= new Throwable().getStackTrace()[0].getMethodName().toString();
		ConcurrentHashMap<String, Object> getScriptData= getDataScript.get(testCaseName);

		loginPage.launchURL()
				.clickOnSingInHeader()
			.clickUsername()
				.typeUserName(getScriptData.get("Email").toString())
					.clickPassword()
				.typePassword(getScriptData.get("Password").toString())
			.clickSignIn()
		.waitForHomePageHeader(getScriptData.get("UserName").toString());

		log.info("Hey..! Login Successful....!");
     }

	@Test(description = "Verify LogIn with invalid username & password")
	public void signIn_WithInvalidCredentials() throws Exception {

		String testCaseName= new Throwable().getStackTrace()[0].getMethodName().toString();
		ConcurrentHashMap<String, Object> getScriptData= getDataScript.get(testCaseName);

		loginPage.launchURL()
				.clickOnSingInHeader()
				.clickUsername()
				.typeUserName(getScriptData.get("Email").toString())
				.clickPassword()
				.typePassword(getScriptData.get("Password").toString())
				.clickSignIn()
				.validateInvalidCredentialError();
	}



	}