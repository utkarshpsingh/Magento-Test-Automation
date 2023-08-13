package main.java.org.magento.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import main.java.org.magento.base.Utilites;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporting {

    ExtentHtmlReporter htmlReporter= null;
    ExtentReports extentReport=null;

    Utilites utilites= null;
    static String resultFolderName="";
    public ExtentReports initializeExtentReport() throws Exception {

        utilites= new Utilites();
        utilites.moveFilesToAnotherDirectory(System.getProperty("user.dir")+"/CurrentTestResult",
                System.getProperty("user.dir")+"/ArchivedTestResults");

        String timestamp= getCurrentTimeStamp();
        resultFolderName= "AutomationResult_"+timestamp;

        htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/CurrentTestResult/"+resultFolderName+"/MagentoReport.html");
        extentReport = new ExtentReports();

        htmlReporter.config().setDocumentTitle("Magento-TestAutomation-Report");
        htmlReporter.config().setReportName("WebAutomation");
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setChartVisibleOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);

        extentReport.attachReporter(htmlReporter);

        extentReport.setSystemInfo("OS","Windows 11");
        extentReport.setSystemInfo("Browser",System.getProperty("browser").toUpperCase());
        extentReport.setSystemInfo("User","Utkarsh Pratap Singh");

        return extentReport;
    }


    public void getResult(ITestResult result, ExtentTest test ) {

        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getTestName());
        }
        else {
            test.log(Status.SKIP, result.getTestName());
        }
    }


    public String takeScreenshot(WebDriver driver,ITestResult result) throws IOException {
        // Creating instance of File

        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();

        String filePath= System.getProperty("user.dir")
                +"/CurrentTestResult/"+resultFolderName+"/Screenshots/Magento_"+result.getMethod().getMethodName()+"_"+getCurrentTimeStamp()+".png";

        File File = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(File,new File(filePath));

        return filePath;
    }


    public String getCurrentTimeStamp(){

        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();

        String timeStamp=dateFormat.format(date).replace(" ","");

        return timeStamp;


    }



}
