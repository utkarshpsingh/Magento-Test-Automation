package main.java.org.magento.objectsRepository;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage {

	@FindBy(xpath = "//div[@class='panel header']//a[text()='Create an Account']")
	public WebElement createAccountLink;
	@FindBy(xpath = "//input[@id='firstname']") public WebElement inputFirstName;
	@FindBy(xpath = "//input[@id='lastname']") public WebElement inputLastName;
	@FindBy(xpath = "//input[@name='email']") public WebElement inputEmail;
	@FindBy(xpath = "//input[@name='password']") public WebElement inputPassword;
	@FindBy(xpath = "//input[@name='password_confirmation']") public WebElement inputConfirmPassword;
	@FindBy(xpath = "//button[@title='Create an Account']") public WebElement btnSubmit;

	 
}
