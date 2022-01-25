package com.crm.comcast.contacttest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.comcast.genericutility.BaseAnnoationClass;
import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;
import com.crm.comcast.genericutility.JavaUtlity;
import com.crm.comcast.genericutility.WebDriverUtility;
import com.crm.comcast.objectrepositorylib.ContactInfomation;
import com.crm.comcast.objectrepositorylib.Contacts;
import com.crm.comcast.objectrepositorylib.CreateNewConatcts;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;

public class ContactTest extends BaseAnnoationClass{
	
	@Test(groups = "smokeTest" , priority = 1)
	public void createconatctTest() throws Throwable {
		/*read test data from Excel File*/
		String lastName = eLib.getDataFromExcel("contact", 1, 10) +jlib.getRandomNumber() ;		
		/*step 2 : Navigate to Contact Page*/
	     Home hp = new Home(driver);
	     hp.getContactLnk().click();
		
		/*step 3 : Navigate create new Contact Page */
	     Contacts cp = new Contacts(driver);
	     cp.getCreateContactImg().click();
       
		/*step 4 : Create Contact */
        CreateNewConatcts cnp = new CreateNewConatcts(driver);
        cnp.createConatct(lastName);
		
		/*step 5 : Verify the Contact details*/
        ContactInfomation ci = new ContactInfomation(driver);
        String actLstName = ci.getOrgHeaderSucMsg().getText();

        Assert.assertTrue(actLstName.contains(lastName));
 
	}
	@Test(groups = "regressionTest" , priority = 2)
	public void createContactWithOrgTest() throws Throwable {	
		/*read test data from Excel File*/
		String contactName = eLib.getDataFromExcel("contact", 4, 2) +jlib.getRandomNumber() ;
		String orgName = eLib.getDataFromExcel("contact", 4, 3) +jlib.getRandomNumber() ;

	     /*step 2 : Navigate to Org Page*/
	     Home hp = new Home(driver);
	     hp.getOrgLink().click();
			
	    /*step 3 : Navigate create new Org Page */
	    Organizations op = new Organizations(driver);
	   op.getCreatenewOrgImg().click();
	        
	    /*step 4 : Create Org */
	     CreatingNewOrganization cno = new CreatingNewOrganization(driver);
	     cno.createOrg(orgName);
	     
	     /*wait for header element*/
	     OrganizationInformation oi = new OrganizationInformation(driver);
	     wLib.waitForElemnetToVisible(driver, oi.getOrgHeaderSucMsg());
	     
	     Assert.assertTrue(oi.getOrgHeaderSucMsg().getText().contains(orgName));
	     
	   /*step 5 : navigate to Contact Page*/
	     hp.getContactLnk().click();
	     
	   /*step 6 : navigate to Create new Contact Page*/  
	     Contacts cp = new Contacts(driver);
	     cp.getCreateContactImg().click();
	     
	   /*step 7 : create a new Contact with orgName Page*/ 
	     CreateNewConatcts cnp = new CreateNewConatcts(driver);
	   cnp.createConatct(contactName, orgName);
	    
	    
	    /*step 8 : verify the details*/ 
       ContactInfomation ci = new ContactInfomation(driver);
       String actLstName = ci.getOrgHeaderSucMsg().getText();
      Assert.assertTrue(actLstName.contains(contactName));
       
       String actOrgNAme = ci.getOrgNAmeInfo().getText();
       System.out.println(actOrgNAme);
       SoftAssert soft = new SoftAssert();
       soft.assertEquals(actOrgNAme.trim(), orgName);
       soft.assertAll();
	     

	     
	}
	
	


}
