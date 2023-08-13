package main.java.org.magento.pageFunctions;

import main.java.org.magento.base.BaseSetupClass;
import main.java.org.magento.base.ReusableFunctions;
import main.java.org.magento.objectsRepository.CreateAccountPage;
import main.java.org.magento.objectsRepository.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountController extends ReusableFunctions {

    Logger log = LogManager.getLogger(CreateAccountController.class);
    LoginPage login = null;
    CreateAccountPage createAccountPage= null;

    public CreateAccountController(WebDriver driver) throws Exception	{

        super(driver); //  driver instance of ReusableFunctions class that all the page objects inherit from
        login = PageFactory.initElements(driver, LoginPage.class);
        createAccountPage = PageFactory.initElements(driver, CreateAccountPage.class);
    }

    public CreateAccountController launchURL() throws Exception{
        launchURL(BaseSetupClass.getURL());
        return this;
    }

    public CreateAccountController clickOnCreateAccountLink() throws Exception	{

        clickObject(createAccountPage.createAccountLink, "Click Account Link");
        return this;
    }

    public CreateAccountController clickOnFirstNameField() throws Exception	{

        clickObject(createAccountPage.inputFirstName, "FirstName");
        return this;
    }

    public CreateAccountController typeFirstName(String firstName) throws Exception	{

        typeValue(createAccountPage.inputFirstName, "firstName", firstName);
        return this;
    }

    public CreateAccountController clickOnLastNameField() throws Exception	{

        clickObject(createAccountPage.inputLastName, "LastName");
        return this;
    }

    public CreateAccountController typeLastName(String lastName) throws Exception	{

        typeValue(createAccountPage.inputLastName, "LastName", lastName);
        return this;
    }


    public CreateAccountController clickOnEmailField() throws Exception	{

        clickObject(createAccountPage.inputEmail, "Email");
        return this;
    }

    public CreateAccountController typeEmail(String email) throws Exception	{

        typeValue(createAccountPage.inputEmail, "Email", email);
        return this;
    }

    public CreateAccountController clickOnPasswordField() throws Exception	{

        clickObject(createAccountPage.inputPassword, "Password");
        return this;
    }

    public CreateAccountController typePassword(String password) throws Exception	{

        typeValue(createAccountPage.inputPassword, "Password", password);
        return this;
    }

    public CreateAccountController clickOnConfirmPasswordField() throws Exception	{

        clickObject(createAccountPage.inputConfirmPassword, "Password");
        return this;
    }

    public CreateAccountController typeConfirmPassword(String confirmPassword) throws Exception	{

        typeValue(createAccountPage.inputConfirmPassword, "Confirm Password", confirmPassword);
        return this;
    }

    public CreateAccountController clickOnCreateAnAccountButton() throws Exception	{

       //clickObject(createAccountPage.btnSubmit, "Create An Account");
        return this;
    }



}
