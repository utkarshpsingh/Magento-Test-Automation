# TestFramework

Hybrid Test Automation Framework

This Framework is based on Page Object Model using Selenium Webdriver + java, TestNg and Maven: Page Object Model is an object design pattern in Selenium, where web pages are represented as classes, and the various elements on the page are defined as variables on the class.

*  Selenium WebDriver is an object-oriented automation API that natively drives a browser as a user would. The Test scripts      and functions will be written in java

*  TestNG is an automation testing framework in which NG stands for "Next Generation". TestNG is inspired from JUnit which 
   uses the annotations (@)

*  Maven is a tool which is a way to manage dependency for Java Based Project

Packages: 


Under src/main/java:


1 ) base : This package contains core classes for the framework i.e setting up the drivers, re-usability of functions, Utilities and DataProvider class


2)  controller : user interactions as methods are implemented in the classes under this package and object of these
           classes should be registered in Application controller to pass the driver instance


3)  data : This package contains loading data properties from external resources i.e excel files etc..


4)  Object Repository : PageFactory in Selenium is an extension to Page Object and can be used in various ways. In this case we will            use Page Factory to initialize web elements that are defined in web page classes or Page Objects

Under src/test/java:

5) pageFunctions : Function related to the pages of the application

6) report: Custom extend report class.


Under src/test/java: All the test cases class are available, they segregate pages/functionality wise.

Folder testngXmlFiles: Contains all the xml files like regression.xml, sanity.xml

Folder Logs: Contains all the running test log of the application.

Folder Input: Contains excel test data file

Folder Config: Contains the properties file where Environment, URL, Browser and Wait times are available.

Folder CurrentTestResult: Contains the current execution Extent Report html and screenshot file.

Folder ArchivedTestResults: Contains all the previous Extent report html files


**Note:**
1- Command Line Execution: Go to the Magento_Automation folder 
cd "path of the project" mvn clean install

2- We can do parallel execution changing the thread-count value in the test NG xml files.