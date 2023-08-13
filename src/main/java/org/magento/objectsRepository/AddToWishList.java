package main.java.org.magento.objectsRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToWishList {


    @FindBy(xpath = "//div[@class='panel header']//a[text()='My Wish List ']")
    public WebElement myWishListLink;
    @FindBy(xpath = "//a[@title='Remove Item']") public WebElement removeItem;


}
