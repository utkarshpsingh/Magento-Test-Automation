package main.java.org.magento.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import main.java.org.magento.controller.ApplicationController;
import main.java.org.magento.controller.TestScenarioDataController;
import main.java.org.magento.report.ExtentReporting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class BaseSetupClass {


	Logger log = LogManager.getLogger(BaseSetupClass.class);
	Properties objProperties = new Properties();
	{
		try {
			objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ApplicationController App = null;
	public Utilites commonFunctions = new Utilites();
	public static Integer implicitWait =10;
	public static Integer pageLoadTimeout =15;
	public static Integer explicitWait =20;
	public TestScenarioDataController dataController =null;
	private ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> getData;
	public static ExtentReporting extentReporting;
	public static ExtentReports extentReports;
	public ITestResult result;
	public static ExtentTest test;

	// ==========================BROWSER VARIABLES============================================
	public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
	public static final String CHROME_DRIVER_EXE = "/Lib/chromedriver.exe";
	public static final String IE_DRIVER_KEY = "webdriver.ie.driver";
	public static final String IE_DRIVER_EXE = "/Lib/IEDriverServer.exe";
	public static final String FIREFOX_DRIVER_KEY = "webdriver.gecko.driver";
	public static final String FIREFOX_DRIVER_EXE = "/Lib/geckodriver.exe";

	public String browser = null;
	public BROWSER selectedBrowser;
	private WebDriver localDriver;
	private static String environment = null;
	public String ConfigFile = "Config/CONFIG.properties";
	public Properties config;
	private static String URL = null;

	public BaseSetupClass(){
		dataController= new TestScenarioDataController();

	}
	public enum BROWSER {
		firefox, ie, chrome
	}

	@BeforeSuite
	public synchronized void initializing() throws Exception {

		selectedBrowser = BROWSER.valueOf(objProperties.getProperty("Browser").toLowerCase());
		System.setProperty("browser", selectedBrowser.toString());

		if(extentReports ==null){
			extentReports= new ExtentReports();
		}
		if(extentReporting ==null){
			extentReporting= new ExtentReporting();
		}

		extentReports= extentReporting.initializeExtentReport();

		if(getData==null){
			dataController= new TestScenarioDataController();
				getData= dataController.getDataForSheetTestData();
		}

		environment = objProperties.getProperty("Environment");
		System.setProperty("environment", environment);
		setEnvironment(environment);
		URL= objProperties.getProperty("URL");
		implicitWait= Integer.valueOf(objProperties.getProperty("ImplicitWait"));
		pageLoadTimeout= Integer.valueOf(objProperties.getProperty("PageLoanTimeOut"));
		explicitWait= Integer.valueOf(objProperties.getProperty("ExplicitWait"));

	}

	@BeforeMethod
	public synchronized void LaunchBrowser() throws Exception {

		selectedBrowser = BROWSER.valueOf(objProperties.getProperty("Browser").toLowerCase());

		try {
			switch (selectedBrowser) {

			case firefox: {

				System.setProperty(FIREFOX_DRIVER_KEY, System.getProperty("user.dir")+ FIREFOX_DRIVER_EXE);
				localDriver = new FirefoxDriver();
				break;
			}

			case chrome: {

				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				options.setExperimentalOption("prefs", chromePrefs);
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));

				System.setProperty(CHROME_DRIVER_KEY, System.getProperty("user.dir")+ CHROME_DRIVER_EXE);
				localDriver = new ChromeDriver(options);

				log.info("DRIVER INITIALISED....!");

				break;
			}

			case ie: {

				System.setProperty(IE_DRIVER_KEY, System.getProperty("user.dir")+ IE_DRIVER_EXE);
				localDriver = new InternetExplorerDriver();

				break;
			}
			default: {
				log.error("Error: NO DRIVER INITIALISED");
			}

			}


		} catch (Throwable exp) {
			log.error("Exception in browser initialization!!! : " + exp.getMessage());
		}

		localDriver.manage().deleteAllCookies();
		localDriver.manage().window().maximize();
		localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		localDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));

		setDriver(localDriver);

	}//LaunchBrowser


	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		test= extentReports.createTest(result.getMethod().getDescription());

		if(result.getStatus() == ITestResult.FAILURE) {
			test.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(extentReporting.takeScreenshot(localDriver,result)).build());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getMethod().getDescription());
		}
		else {
			test.log(Status.SKIP, result.getMethod().getMethodName());
		}

		this.App=null;
		localDriver.quit();
	}

	@AfterSuite
	public void tearDown(){

		extentReports.flush();
	}

	public static String getEnvironment() {
		return environment;
	}

	public void setDriver(WebDriver wdriver) {
		wdriver= this.localDriver;
	}

	public void setURL(String url){
		url= this.URL;
	}

	public synchronized ConcurrentHashMap<String, ConcurrentHashMap<String, Object>> getTestData() throws IOException {
		if (getData!=null){
			return getData;
		}else {
			getData= dataController.getDataForSheetTestData();
		}
		return getData;
	}

	public WebDriver getDriver() {
		return localDriver;
	}

	public static String getURL(){
		return URL;
	}

	public ApplicationController App() {
		if (App == null) {
			App = new ApplicationController(getDriver());
		}
		return App;
	}


	public void setEnvironment(String environment) {
		BaseSetupClass.environment = environment;
			System.out.println("Starting execution on the -> "+environment+" environment");
	}




}