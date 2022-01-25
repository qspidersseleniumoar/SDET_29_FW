package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericutility.WebDriverUtility;

public class Home extends WebDriverUtility{
	WebDriver driver;
	
	   public Home(WebDriver driver) {             //R3
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	  }
	   
	   @FindBy(linkText = "Organizations")
	   private WebElement orgLink;
	   
	   @FindBy(linkText = "Contacts")
	   private WebElement contactLnk;
	   
	   @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	   private WebElement adminImg;
	   
	   @FindBy(linkText = "Sign Out")
	   private WebElement signOutLnk;

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLnk() {
		return contactLnk;
	}
	   /**
	    * used for app logout
	    */
	public void logout() {
		mouseOverOnElemnet(driver, adminImg);
		signOutLnk.click();
	}
	   
	   
}
