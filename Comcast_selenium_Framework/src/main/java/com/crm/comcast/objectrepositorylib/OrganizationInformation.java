package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformation {
	WebDriver driver;
	
	   public OrganizationInformation(WebDriver driver) {             //R3
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	  }
	@FindBy(className = "dvHeaderText")
   private WebElement orgHeaderSucMsg;

	
	@FindBy(id = "dtlview_Industry")
	private WebElement indutriesInfo;
	

	
	
	public WebElement getIndutriesInfo() {
		return indutriesInfo;
	}




	public WebElement getOrgHeaderSucMsg() {
		return orgHeaderSucMsg;
	}
	
	
	
}






