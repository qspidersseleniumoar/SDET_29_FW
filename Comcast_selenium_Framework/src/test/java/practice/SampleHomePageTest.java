package practice;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseAnnoationClass;

public class SampleHomePageTest  extends BaseAnnoationClass{
	
	
	@Test
	public void veifyHomePageTest() {
		String expectedPage = "Contact";
		
		String actTitle = driver.getTitle();
		boolean  status = actTitle.contains(expectedPage);
		Assert.assertTrue(status , "Home page is not verified==>FAIL");
		System.out.println("Home page is  verified==>PASS");
		
	
	}
	
	@Test
	public void verifyHomePageLogoTest() {
		
		boolean imgStatus = driver.findElement(By.xpath("//img[@alt='vtiger-crm-logo.gif']")).isDisplayed();
		Assert.assertTrue(imgStatus ,"Home page logo is not verified==>FAIL");
		System.out.println("Home page logo is  verified==>PASS");
	}

}
