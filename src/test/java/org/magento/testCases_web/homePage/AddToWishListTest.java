package test.java.org.magento.testCases_web.homePage;

import main.java.org.magento.base.BaseSetupClass;
import main.java.org.magento.base.ReusableFunctions;
import main.java.org.magento.pageFunctions.HomeController;
import main.java.org.magento.pageFunctions.LoginController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ConcurrentHashMap;

public class AddToWishListTest extends BaseSetupClass {

    static Logger log = LogManager.getLogger(AddToWishListTest.class);
    public ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> getDataScript= null;
    public WebDriver driver= null;
    public ReusableFunctions functions= null;
    HomeController homeController;
    LoginController loginController;

    @BeforeMethod(alwaysRun= true)
    public void init() throws Exception {

        getDataScript= getTestData();
        driver= getDriver();
        functions= new ReusableFunctions(driver);
        homeController =App().HomeController();
        loginController= App().LoginController();
    }


    @Test(description = "Add item To wishlist and verify it in wishlist page")
    public void addItemToWishlist() throws Exception {

        String testCaseName= new Throwable().getStackTrace()[0].getMethodName().toString();
        ConcurrentHashMap<String, Object> getScriptData= getDataScript.get(testCaseName);

        loginController.loginToApplication(getScriptData.get("Email").toString(),
                                                getScriptData.get("Password").toString());
        homeController.clickOnGearLink()
                        .clickOnWatchesCategory()
                                .searchForTheItemAndAddItToWishlist(getScriptData.get("ItemName").toString())
                                        .clickOnUserDropDown()
                .verifyItemAvailableInWishlist(getScriptData.get("ItemName").toString())
                    .removeItemFromWishList(getScriptData.get("ItemName").toString());

        log.info("Hey..! Account Created Successfully....!");
    }





}
