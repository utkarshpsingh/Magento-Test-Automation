package main.java.org.magento.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.*;

/**
 * @author utkarshsingh
 *
 */

public class ReusableFunctions{

	Logger log = LogManager.getLogger(ReusableFunctions.class);

	public Properties objCMPProperties = new Properties();
	public Utilites objUtilities = new Utilites();
	public String strErrorMsg = "";
	public WebDriver driver;
	protected JavascriptExecutor JSExecutor;
	public WebElement waitElement;
	public WebDriverWait wait;

	public static HashMap contextData= new HashMap<String, Object>();
	
	public enum waitCondition {
			toBeVisible, toBeClickable, toBeInvisible, tobePresent
		}
	
	//==============================CONSTRUCTOR FOR COMPONENT REUSABLE FUNCTIONS=================
	public ReusableFunctions(WebDriver objTempWebDriver) throws Exception	{
	
	//==========================INITIALIZE THE WEBDRIVER OBJECT INSIDE COMPONENT REUSABLE FUNCTIONS======
	      driver = objTempWebDriver;
	      JSExecutor = (JavascriptExecutor)driver;
	      wait = new WebDriverWait(driver, Duration.ofSeconds(BaseSetupClass.explicitWait));
		  contextData= new HashMap<String, Object>();
	}

	public synchronized Boolean launchURL(String strURL) throws Exception
	{
		try
		{
			driver.get(strURL);
					log.info(strURL + " launched in browser");
					return true;
		}
		catch(Exception objException)
		{
					strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
					log.error(strErrorMsg);

				return false;
		}
	}
	
	public synchronized Boolean clickObject(WebElement objWebElement, String strObjectName) throws Exception
	{
		try
		{
			waitForElement(objWebElement);
			Thread.sleep(2000);
				objWebElement.click();
			log.info(strObjectName +  " "+  "Clicked");
			return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
			log.error(strObjectName +  " "+  "Not Clicked");
			return false;
		}
	}
	
	public synchronized Boolean clickObjectJavascript(WebElement objWebElement, String strObjectName) throws Exception
	{
		try
		{
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", objWebElement);
			log.info(strObjectName +  " "+  "Clicked");
			return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
			log.error(strObjectName +  " "+  "Not Clicked");
			return false;
		}
	}
	
	public synchronized Boolean clickObjectAction(WebElement objWebElement, String strObjectName) throws Exception
	{
		try
		{
			waitForElement(objWebElement);
			Actions actions = new Actions(driver);
	        actions.moveToElement(objWebElement).click().build().perform();;
			Thread.sleep(1000);
			log.info(strObjectName +  " "+  "Clicked");
			return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
			log.error(strObjectName +  " "+  "Not Clicked");
			return false;
		}
	}

	public boolean isAlertPresent(){
	      try{
	          driver.switchTo().alert();
	          return true;
	      }catch(NoAlertPresentException ex){
	          return false;
	      }
	}

	public synchronized Boolean typeValue(WebElement objWebElement, String strObjectName, String strInputValue   ) throws Exception
	{
		try
		{
			objWebElement.sendKeys(strInputValue);
				Thread.sleep(1000);
			log.info(strInputValue + " "+ "Typed in " + " "+ strObjectName );
					return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);

			log.error(strInputValue + " "+ "Not typed " );
				return false;
		}
	}

	public synchronized Boolean selectFromDropDown(WebElement objWebElement, String strObjectName, String strValue) throws Exception
	{
		try
		{
			Select dropdown = new Select(objWebElement);
			Thread.sleep(2000);
			dropdown.selectByValue(strValue);
			//dropdown.selectByVisibleText(strValue);
				Thread.sleep(2000);
			log.info(strValue + " "+ "Selected from DropDown ");
					return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
				log.error(strValue + " "+ "Not Selected from DropDown \"" );

			return false;
		}
	}

	public Boolean selectFromDropDownVisibleText(WebElement objWebElement, String strObjectName, String strValue) throws Exception
	{
		try
		{
			Select dropdown = new Select(objWebElement);
		//	dropdown.selectByValue(strValue);
			dropdown.selectByVisibleText(strValue);
				Thread.sleep(2000);
			log.info(strValue + " "+ "Selected from DropDown ");
					return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
				log.error(strValue + " "+ "Not Selected from DropDown \"" );

			return false;
		}
	}

	public Boolean selectFromDropDownbyindex (WebElement objWebElement, String strObjectName, int strindex) throws Exception
	{
		try
		{
			Select dropdown = new Select(objWebElement);
		//	dropdown.selectByValue(strValue);
			dropdown.selectByIndex(strindex);
				Thread.sleep(2000);
			log.info(strindex + " "+ "Selected from DropDown ");
					return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
				log.error(strindex + " "+ "Not Selected from DropDown \"" );

			return false;
		}
	}

	public synchronized Boolean selectFromDropDownIndex(WebElement objWebElement, String strObjectName, int strIndex) throws Exception
	{
		try
		{
			Select dropdown = new Select(objWebElement);
			dropdown.selectByIndex(strIndex);
				Thread.sleep(1000);
			log.info(strIndex + " "+ "Selected from DropDown ");
					return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
				log.error(strIndex + " "+ "Not Selected from DropDown " );

			return false;
		}
	}

	public synchronized Boolean clearNTypeValue(WebElement objWebElement, String strInputValue, String strObjectName) throws Exception
	{
		try{
			objWebElement.clear(); 
				Thread.sleep(500);
					objWebElement.sendKeys(strInputValue);
			return true;
		}
		
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
				log.error(strInputValue + " "+ "Not typed " );

			return false;
		}
	}

	public synchronized boolean waitForElement(WebElement objWebElement) throws Exception {

		try{
		waitElement = wait.until(ExpectedConditions.visibilityOf(objWebElement));
			waitElement.isDisplayed();
			
			return true;
		}catch(Exception objException){
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
			log.error(strErrorMsg);

		return false;
	}
	
	}

	public synchronized void waitForElement(waitCondition condition, WebElement element) throws Exception {
		
		switch (condition) {
		case tobePresent:
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocatorFromElement(element)));
			break;
		case toBeVisible:
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocatorFromElement(element)));
			break;
		case toBeInvisible:
			wait.until(ExpectedConditions.invisibilityOfElementLocated(getLocatorFromElement(element)));
			break;
		case toBeClickable:
			wait.until(ExpectedConditions.elementToBeClickable(getLocatorFromElement(element)));
			break;
		default:
			break;
		}		
	}

	public By getLocatorFromElement(WebElement we) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

			// By format = "[foundFrom] -> locator: term"
			// see RemoteWebElement toString() implementation
			String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
			String locator = data[0];
			String term = data[1];

			switch (locator) {
				case "xpath":
					return By.xpath(term);
				case "css selector":
					return By.cssSelector(term);
				case "id":
					return By.id(term);
				case "tag name":
					return By.tagName(term);
				case "name":
					return By.name(term);
				case "link text":
					return By.linkText(term);
				case "class name":
					return By.className(term);
			}
			return (By) we;
		
	}

	public synchronized String getText(WebElement element, String strObjectName){

		String eleText="";

		try {
			Thread.sleep(2000);
			waitForElement(element);
			eleText= element.getText();

			if(eleText.isEmpty()) {
				eleText = element.getAttribute("innerText");
			}
			if(eleText.isEmpty()) {
				eleText = element.getAttribute("outerText");
			}
			if(eleText.isEmpty()) {
				eleText = element.getAttribute("textContent");
			}
			if(eleText.isEmpty()) {
				eleText = ((JavascriptExecutor)driver).executeScript("return arguments[0].value;",element).toString();
			}

			log.info(eleText + " "+ "Extracted from element");

		} catch (Exception e) {
			log.error("Exception: ", e.getMessage());
		}
		return eleText;
	}

	public static void setContextData(String key, Object value){
		contextData.put(key,value);
	}

	public static Object getContextData(String key){
		return contextData.get(key);
	}

	public synchronized void elementPresentOrEnabled(WebElement objWebElement, String  getStatus, String strObjectName) throws InterruptedException{
        try{
            if(getStatus.equals("Enabled")){
                if(objWebElement.isEnabled())
					log.info(strObjectName + "" + " is Enabled on the page");
            }
            if(getStatus.equals("Present")){
                if(objWebElement.isDisplayed())
					Assert.assertEquals(objWebElement.isDisplayed(),true,strObjectName+" is present on the screen");
					log.info(strObjectName + "" + " is Present on the page");
                
            }
        }catch(org.openqa.selenium.NoSuchElementException nse){
			log.error(nse);
		}
	}

  	public synchronized boolean scrollIntoView(WebElement element, String strObjectName) throws Exception {
  		try
		{
  			JSExecutor.executeScript("return arguments[0].scrollIntoView({inline:'center'});", element );
			log.info( strObjectName+ " "+ "scrolled into view ");
					return true;
		}
		catch(Exception objException)
		{
			strErrorMsg = objUtilities.GetExceptionNDisplay(objException, true);
			
			log.error(strObjectName + " "+ "not bale to scrolled into view \"" );
				return false;
		}
  	}

  	public boolean switchToFrame(WebElement element){
	      try{
	          driver.switchTo().frame(element);
	          return true;
	      }catch(Exception ex){
	          return false;
	      }
	}

	public boolean switchToMainFrame(){
	      try{
	          driver.switchTo().parentFrame();
	          return true;
	      }catch(Exception ex){
	          return false;
	      }
	}

	public void switchToNewWindow() {
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
	}

	public void switchToOldWindow() {
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.close();
	    driver.switchTo().window(newTab.get(0));
	}

	public void switchToWindow(String windowTitle) {
		
		String parentWindow= driver.getWindowHandle();
		
			Set<String> handler= driver.getWindowHandles();
				Iterator<String> itr=handler.iterator();
			
		while(itr.hasNext()) {
			
			if(driver.switchTo().window(itr.next()).getTitle().contains(windowTitle)) {
				
			}else {
				driver.switchTo().window(parentWindow);
			}			
		}
		
	}

	public WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor)driver)
							.executeScript("return arguments[0].shadowRoot", element);
		return ele;
	}
	public void moveToElement(WebElement objWebElement, String strObjectName) {
 		 //Instantiate Action Class        
       Actions actions = new Actions(driver);
	   	//Mouse hover action on element 
	   	actions.moveToElement(objWebElement).perform();
 	}

	public String getBrowserUrl(){
	      return driver.getCurrentUrl();
	}

	public Object runJS(String script) {
		
		JavascriptExecutor je = (JavascriptExecutor)driver;
		return je.executeScript(script);
	}


	
}
