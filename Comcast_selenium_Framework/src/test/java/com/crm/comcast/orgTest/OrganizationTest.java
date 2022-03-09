package com.crm.comcast.orgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseAnnoationClass;
import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.FileUtility;
import com.crm.comcast.genericutility.JavaUtlity;
import com.crm.comcast.genericutility.WebDriverUtility;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;

@Listeners(com.crm.comcast.genericutility.ListenerImplementation.class)
public class OrganizationTest extends BaseAnnoationClass{

@Test(groups = "smokeTest")
public void createOrganization() throws Throwable {		
		/*read test data from Excel File*/
		String orgName = eLib.getDataFromExcel("org", 1, 10) +jlib.getRandomNumber() ;

		/*step 2 : Navigate to Org Page*/
        Home hp = new Home(driver);
        hp.getOrgLink().click();
		
		/*step 3 : Navigate create new Org Page */
		Organizations op = new Organizations(driver);
		op.getCreatenewOrgImg().click();
        
		/*step 4 : Create Org */
        CreatingNewOrganization cno = new CreatingNewOrganization(driver);
        cno.createOrg(orgName);
		
		/*step 5 : Verify the Org details*/
        OrganizationInformation oi = new OrganizationInformation(driver);
        String actOrgSucMsg = oi.getOrgHeaderSucMsg().getText();
       Assert.assertTrue(actOrgSucMsg.contains(orgName));    
	}
@Test(groups = "regressionTest")
  public void createOrgWithIndustriesTypeTest() throws Throwable {

		/*read test data from Excel File*/
		String orgName = eLib.getDataFromExcel("org", 4, 2) +jlib.getRandomNumber() ;
		String industries = eLib.getDataFromExcel("org", 4, 3);

		/*step 2 : Navigate to Org Page*/
		Home hp = new Home(driver);
		hp.getOrgLink().click();
		
		/*step 3 : Navigate create new Org Page */
		Organizations op = new Organizations(driver);
		op.getCreatenewOrgImg().click();
		
		/*step 4 : Create Org */
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);
		cno.createOrg(orgName, industries);
		
		/*step 5 : Verify the Org details*/
		OrganizationInformation oi = new OrganizationInformation(driver);
		String actOrgSucMsg = oi.getOrgHeaderSucMsg().getText();
		 Assert.assertTrue(actOrgSucMsg.contains(orgName)); 
		
		String actIndutriesInfo = oi.getIndutriesInfo().getText();
	   Assert.assertEquals(actIndutriesInfo, industries);

}

}
