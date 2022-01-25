package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.By;
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
public class CreateNewConatcts extends WebDriverUtility{
	WebDriver driver;
	
	   public CreateNewConatcts(WebDriver driver) {             //R3
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	  }
	   
	   @FindBy(name = "lastname")
	   private WebElement lastNameEdt;
	   
	   @FindBy(xpath = "//input[@title='Save [Alt+S]']")
	   private WebElement saveBtn;
       
	   @FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	   private WebElement orgLookUpImg;
	   
	   
	   
	   /**
	    * used to create new contact with mandate information
	    * @param conatctLastName
	    */
    public void createConatct(String conatctLastName) {
    	
    	lastNameEdt.sendKeys(conatctLastName);
    	saveBtn.click();
    }
    
	   /**
	    * used to create new contact with orgName information
	    * @param conatctLastName
	    */
 public void createConatct(String conatctLastName , String orgName) {
 	
 	lastNameEdt.sendKeys(conatctLastName);
 	orgLookUpImg.click();
 	swithToWindow(driver, "Accounts&action"); 	
 	Organizations op = new Organizations(driver);
 	op.getSearchEdt().sendKeys(orgName);
 	op.getSearchBtn().click();
 	driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
 	swithToWindow(driver, "Contacts&action");
 	saveBtn.click();
 }
}








