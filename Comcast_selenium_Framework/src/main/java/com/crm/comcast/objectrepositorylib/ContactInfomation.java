package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfomation {
	WebDriver driver;
	
	   public ContactInfomation(WebDriver driver) {             //R3
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
	  }
	@FindBy(className = "dvHeaderText")
   private WebElement orgHeaderSucMsg;


	@FindBy(id = "mouseArea_Organization Name")
	private WebElement orgNAmeInfo;
	
	


	public WebElement getOrgNAmeInfo() {
		return orgNAmeInfo;
	}




	public WebElement getOrgHeaderSucMsg() {
		return orgHeaderSucMsg;
	}
	
	
	
}






