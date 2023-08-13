package test.java.org.magento.testCases_web.createAccountPage;

import main.java.org.magento.base.BaseSetupClass;
import main.java.org.magento.base.ReusableFunctions;
import main.java.org.magento.pageFunctions.CreateAccountController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ConcurrentHashMap;

public class CreateAccountTest extends BaseSetupClass {

    static Logger log = LogManager.getLogger(CreateAccountTest.class);
    public ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> getDataScript= null;
    public WebDriver driver= null;
    public ReusableFunctions functions= null;
    CreateAccountController createAccountController;

    @BeforeMethod(alwaysRun= true)
    public void init() throws Exception {

        getDataScript= getTestData();
        driver= getDriver();
        functions= new ReusableFunctions(driver);
        createAccountController =App().CreateAccountController();
    }


    @Test(description = "Create user account successfully on the Magento")
    public void createUserAccountSuccessfully() throws Exception {

        String testCaseName= new Throwable().getStackTrace()[0].getMethodName().toString();
        ConcurrentHashMap<String, Object> getScriptData= getDataScript.get(testCaseName);

        createAccountController.launchURL()
            .clickOnCreateAccountLink()
                .clickOnFirstNameField()
                    .typeFirstName(getScriptData.get("FirstName").toString())
                        .clickOnLastNameField()
                            .typeLastName(getScriptData.get("LastName").toString())
                                .clickOnEmailField()
                                    .typeEmail(getScriptData.get("Email").toString())
                .clickOnPasswordField()
                    .typePassword(getScriptData.get("Password").toString())
                        .clickOnConfirmPasswordField()
                            .typeConfirmPassword(getScriptData.get("ConfirmPassword").toString())
                                    .clickOnCreateAnAccountButton();

        log.info("Hey..! Account Created Successfully....!");
    }





}
