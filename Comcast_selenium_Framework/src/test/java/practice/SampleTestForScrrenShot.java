package practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseAnnoationClass;
import com.crm.comcast.genericutility.WebDriverUtility;
import com.crm.comcast.objectrepositorylib.Home;


public class SampleTestForScrrenShot extends BaseAnnoationClass{
	
	
	@Test(retryAnalyzer = com.crm.comcast.genericutility.ReTryImpclass.class)
	public void conatctTest() throws Throwable {
		
	  Home hp = new Home(driver);
	  hp.getContactLnk().click();
	  
	  Assert.assertEquals("A", "B");
		
	}
	
	

}





