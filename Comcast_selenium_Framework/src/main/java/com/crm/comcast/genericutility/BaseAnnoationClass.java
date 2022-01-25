package com.crm.comcast.genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;

public class BaseAnnoationClass {
	
	/*Object  creation for utility*/
	public ExcelUtility eLib = new ExcelUtility();
	public FileUtility fLib = new FileUtility();
	public JavaUtlity jlib = new JavaUtlity();
	public WebDriverUtility wLib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver = null;
	
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void configBS() {
		System.out.println("==========Conntact to DB===============");
	}
	
	//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
		System.out.println("===============Launch the BROWSER====================");
		/*read common data from properties File*/
		String BROWSER = fLib.getPropertyKeyValue("browser");
		String URL = fLib.getPropertyKeyValue("url");
		/*launch the BROWSER*/
		
	     if(BROWSER.equals("chrome")) {
	    	  driver = new ChromeDriver();
	     }else if(BROWSER.equals("firefox")) {
	    	  driver = new FirefoxDriver();
	     }else if(BROWSER.equals("ie")) {
	    	  driver = new InternetExplorerDriver();
	     }
	     driver.get(URL);
	     sDriver = driver;
	}
	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("========Login=======");
		/*read common data from properties File*/
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD= fLib.getPropertyKeyValue("password");
	     /*step 1 : Login*/
	     Login lp = new Login(driver);
	     lp.loginToApp(USERNAME, PASSWORD);
	}
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() {
		System.out.println("========Logout=======");
		/*step 6 : Logout*/
	     Home hp = new Home(driver);
        hp.logout();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("===============close the BROWSER====================");
		/*close the browser*/
	       driver.quit();
	}

	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	public void configAS() {
		System.out.println("==========close DB connections===============");
	}
	
}
