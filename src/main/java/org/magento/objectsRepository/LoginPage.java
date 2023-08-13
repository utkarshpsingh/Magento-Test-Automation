package main.java.org.magento.objectsRepository;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {


	@FindBy(xpath = "//div[@class='panel header']//a[contains(text(),'Sign In')]")
	public WebElement btnSignIn;
	@FindBy(xpath = "//input[@id='email']") public WebElement userName;
	@FindBy(xpath = "//input[@id='pass']") public WebElement enterPassword;
	@FindBy(xpath = "//button[@id='send2' and @class='action login primary']")
	public WebElement btnSubmit;

	@FindBy(xpath = "//div[@data-ui-id='message-error']") public WebElement loginErrorMsg;

		
	 
}
