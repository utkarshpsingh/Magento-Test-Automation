package test.java.org.magento.testCases_web.shoppingCart;

import main.java.org.magento.base.BaseSetupClass;
import main.java.org.magento.base.ReusableFunctions;
import main.java.org.magento.pageFunctions.HomeController;
import main.java.org.magento.pageFunctions.LoginController;
import main.java.org.magento.pageFunctions.ShoppingCartController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ConcurrentHashMap;

public class AddItemToCartTest extends BaseSetupClass {

    static Logger log = LogManager.getLogger(AddItemToCartTest.class);
    public ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> getDataScript= null;
    public WebDriver driver= null;
    public ReusableFunctions functions= null;
    HomeController homeController;
    LoginController loginController;
    ShoppingCartController shoppingCartController;

    @BeforeMethod(alwaysRun= true)
    public void init() throws Exception {

        getDataScript= getTestData();
        driver= getDriver();
        functions= new ReusableFunctions(driver);
        homeController =App().HomeController();
        loginController= App().LoginController();
        shoppingCartController=App().ShoppingCartController();
    }


    @Test(description = "Add item To cart and verify its price on shopping cart",groups = {"SanityTest"})
    public void addItemToCartAndVerifyPrice() throws Exception {

        String testCaseName= new Throwable().getStackTrace()[0].getMethodName().toString();
        ConcurrentHashMap<String, Object> getScriptData= getDataScript.get(testCaseName);

        loginController.loginToApplication(getScriptData.get("Email").toString(),
                getScriptData.get("Password").toString());

        homeController.clickOnGearLink()
                .clickOnWatchesCategory()
                .searchForTheItemAndAddItToCard(getScriptData.get("ItemName").toString())
                .clickOnShowCartButton()
                .clickOnViewEditCartLink();

        shoppingCartController.verifyItemAvailableInCartAndPrice(getScriptData.get("ItemName").toString(),
                                                                        getScriptData.get("Price").toString())
                .removeItemFromCart();


    }




}
