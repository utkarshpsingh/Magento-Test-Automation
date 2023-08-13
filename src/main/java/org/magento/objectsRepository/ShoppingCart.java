package main.java.org.magento.objectsRepository;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCart {

	@FindBy(xpath = "//table[@id='shopping-cart-table']//a[@title='Remove item']") public WebElement removeItem;
	@FindBy(xpath = "//button[@id='top-cart-btn-checkout']") public WebElement proceedToCheckout;
	@FindBy(xpath = "//div[@class='step-title' and text()='Shipping Address']") public WebElement shippingAddressTitle;
	@FindBy(xpath = "//button[@data-role='opc-continue']") public WebElement nextButton;
	@FindBy(xpath = "//span[text()='Review & Payments']") public WebElement review_paymentPage;
	@FindBy(xpath = "//button[@title='Place Order']") public WebElement place_orderButton;

	@FindBy(xpath = "//h1[@class='page-title']//span") public WebElement thanksText;
	@FindBy(xpath = "//div[@class='checkout-success']//a[@class='order-number']") public WebElement orderNumber;
	@FindBy(xpath = "//h1[@class='page-title']//span") public WebElement orderPage;




}
