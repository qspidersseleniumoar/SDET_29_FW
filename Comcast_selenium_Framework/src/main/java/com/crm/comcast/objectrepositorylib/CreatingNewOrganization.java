package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericutility.WebDriverUtility;
/**
 * 
 * @author Deepak
 *
 */
public class CreatingNewOrganization extends WebDriverUtility{
	WebDriver driver;
	
	   public CreatingNewOrganization(WebDriver driver) {             //R3
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	  }
	   
	   @FindBy(name = "accountname")
	   private WebElement orgNameEdt;
	   
	   @FindBy(name = "industry")
	   private WebElement indutiresLst;
	   
	   @FindBy(xpath = "//input[@title='Save [Alt+S]']")
	   private WebElement saveBtn;
	   
	   /**
	    * @author Deepak 
	    * used to create new org with mandate info
	    * @param orgName
	    */
	   public void createOrg(String orgName) {
		   orgNameEdt.sendKeys(orgName);
		   saveBtn.click();
	   }
	   
	   /**
	    * @author Deepak 
	    * used to create new org with orgname & industries
	    * @param orgName
	    */
	   public void createOrg(String orgName , String industries) {
		   orgNameEdt.sendKeys(orgName);
		   select(indutiresLst, industries);
		   saveBtn.click();
	   }
}
