package main.java.org.magento.pageFunctions;

import main.java.org.magento.base.ReusableFunctions;
import main.java.org.magento.objectsRepository.HomePage;
import main.java.org.magento.objectsRepository.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomeController extends ReusableFunctions {

    Logger log = LogManager.getLogger(HomeController.class);
    LoginPage login = null;
    HomePage homePage= null;

    public HomeController(WebDriver driver) throws Exception	{

        super(driver); //  driver instance of ReusableFunctions class that all the page objects inherit from
        login = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    public HomeController clickOnGearLink() throws Exception	{

        clickObject(homePage.gearMenu, "Gear Link");
        return this;
    }

    public HomeController clickOnWatchesCategory() throws Exception	{

        clickObject(homePage.watches, "Watches Link");
        return this;
    }

    public HomeController searchForTheItemAndAddItToWishlist(String itemName) throws Exception {

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]//ancestor::li"));

        moveToElement(ele,itemName);

        WebElement addToWishlist = driver.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]//" +
                        "ancestor::li//a[@data-action='add-to-wishlist']"));

        clickObject(addToWishlist,"Add To Wishlist");
            Thread.sleep(2000);

        return this;
    }

    public HomeController clickOnUserDropDown() throws Exception	{

        clickObject(homePage.userDropDown, "User Details dropdown");
        return this;
    }

    public HomeController verifyItemAvailableInWishlist(String itemName) throws InterruptedException {

        WebElement addToWishlist = driver.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]"));
            elementPresentOrEnabled(addToWishlist,"Present","Add To Wishlist");

        return this;
    }

    public HomeController removeItemFromWishList(String itemName) throws Exception {

        WebElement ele = driver.findElement(By.xpath("//div[@class='products-grid wishlist']//img[@alt='"+itemName+"']"));

            moveToElement(ele,itemName);

        clickObject(homePage.removeItem,"Add To Wishlist");

        return this;
    }

    public HomeController searchForTheItemAndAddItToCard(String itemName) throws Exception {

        WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]//ancestor::li"));

        moveToElement(ele,itemName);

        WebElement addToCart = driver.findElement(By.xpath("//a[contains(text(),'"+itemName+"')]//ancestor::li//button[@title='Add to Cart']"));

        clickObject(addToCart,"Add To Cart");
        Thread.sleep(5000);

        return this;
    }

    public HomeController clickOnShowCartButton() throws Exception {
        clickObject(homePage.showCart,"Show Cart");
        return this;
    }

    public HomeController clickOnViewEditCartLink() throws Exception {
        clickObject(homePage.viewEditCart,"View/Edit Cart");

        return this;
    }



    public HomeController verifyItemPriceInShowCart(String price) throws Exception {

        elementPresentOrEnabled(homePage.priceShowCart, "Present","Price On Show Cart");
        String expectedPrice= getText(homePage.priceShowCart,"Price On Show Cart");

        Assert.assertEquals(expectedPrice, price);

        return this;
    }

}
