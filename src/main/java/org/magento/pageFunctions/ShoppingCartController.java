package main.java.org.magento.pageFunctions;

import main.java.org.magento.base.ReusableFunctions;
import main.java.org.magento.objectsRepository.HomePage;
import main.java.org.magento.objectsRepository.LoginPage;
import main.java.org.magento.objectsRepository.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ShoppingCartController extends ReusableFunctions {

    Logger log = LogManager.getLogger(ShoppingCartController.class);
    LoginPage login = null;
    HomePage homePage= null;
    ShoppingCart shoppingCart= null;
    public ShoppingCartController(WebDriver driver) throws Exception	{

        super(driver); //  driver instance of ReusableFunctions class that all the page objects inherit from
        login = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        shoppingCart= PageFactory.initElements(driver, ShoppingCart.class);
    }


    public ShoppingCartController verifyItemAvailableInCartAndPrice(String itemName, String price) throws Exception {

        WebElement addedToCartItem = driver.findElement(By.xpath("//table//a[text()='"+itemName+"']"));

        elementPresentOrEnabled(addedToCartItem,"Present","Added To cart Item");

        WebElement priceElement = driver.findElement(By.xpath("(//table//a[text()='"+itemName+"']//ancestor::tr//child::span[@class='price-excluding-tax'])[1]"));

        String getPrice= getText(priceElement,"Price");

        Assert.assertEquals(getPrice, price,"The Item Price does not get matched");

        return this;
    }


    public ShoppingCartController removeItemFromCart() throws Exception {
        clickObject(shoppingCart.removeItem,"Remove Item From Cart");
        return this;

    }

    public ShoppingCartController clickOnProceedToCheckout() throws Exception {
        clickObject(shoppingCart.proceedToCheckout,"Proceed To Checkout");
        return this;
    }

    public ShoppingCartController verifyShippingPageAppears() throws Exception {
        elementPresentOrEnabled(shoppingCart.shippingAddressTitle,"Present","Shipping Page Title");
        return this;
    }

    public ShoppingCartController clickOnNextButton() throws Exception {
        scrollIntoView(shoppingCart.nextButton, "Next Button");
        clickObject(shoppingCart.nextButton,"Next Button");
        return this;
    }

    public ShoppingCartController verifyCheckoutPageAppears() throws Exception {
        elementPresentOrEnabled(shoppingCart.review_paymentPage,"Present","PaymentPage Title");

        return this;
    }

    public ShoppingCartController clickOnPlaceOrder() throws Exception {
        clickObject(shoppingCart.place_orderButton,"Place Order Button");
        return this;
    }

    public ShoppingCartController verifyOrderSuccessfullyPlaced() throws Exception {

        waitForElement(shoppingCart.thanksText);
        Thread.sleep(3000);
        String actualText= getText(shoppingCart.thanksText,"Thank Title").trim();
        Assert.assertEquals(actualText, "Thank you for your purchase!", "The Thanks..! msg on the order successful screen is Not correct");

        elementPresentOrEnabled(shoppingCart.orderNumber,"Present","Order Number");
        String orderNumber= getText(shoppingCart.orderNumber,"Order Number");
        contextData.put("OrderNumber",orderNumber);

        log.info("Order successfully place: ********Order-No: "+orderNumber+" ********");

        return this;
    }

    public ShoppingCartController clickOnOrderLink() throws Exception {
        clickObject(shoppingCart.orderNumber,"Order Link");
        return this;
    }

    public ShoppingCartController verifyMyOrderScreenAppears() throws Exception {
        elementPresentOrEnabled(shoppingCart.orderPage,"Present","PaymentPage Title");
        return this;
    }

    public ShoppingCartController VerifyOrderDetailsOnPage() throws Exception {

        String orderNumber_actual= getText(shoppingCart.orderPage,"Order Number");

        Assert.assertEquals(orderNumber_actual.split("#")[1].trim(),contextData.get("OrderNumber"), "The Order Number is coming correct as: "+orderNumber_actual);

        log.info("Order Details Valid Actual:"+orderNumber_actual+" and expected as: "+contextData.get("OrderNumber"));
        return this;
    }


}
