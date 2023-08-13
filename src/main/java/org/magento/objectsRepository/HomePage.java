package main.java.org.magento.objectsRepository;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(xpath = "//div[@class='panel header']//li[@class='greet welcome']//span")
	public WebElement welcomeHeader;
	@FindBy(xpath = "//ul[@id='ui-id-2']//li//span[contains(text(),'Gear')]//parent::a")
	public WebElement gearMenu;
	@FindBy(xpath = "//dl[@class='options']//li//a[text()='Watches']") public WebElement watches;
	@FindBy(xpath = "//div[@class='panel header']//child::button[@class='action switch']")
	public WebElement userDropDown;
	@FindBy(xpath = "//div[@class='panel header']//a[text()='My Wish List ']")
	public WebElement myWishListLink;
	@FindBy(xpath = "//a[@title='Remove Item']") public WebElement removeItem;
	@FindBy(xpath = "//a[contains(@class,'action showcart')]") public WebElement showCart;
	@FindBy(xpath = "//span[contains(text(),'View and Edit Cart')]//parent::a") public WebElement viewEditCart;
	@FindBy(xpath = "//span[@data-bind='html: cart().subtotal_excl_tax']") public WebElement priceShowCart;








	 
}
