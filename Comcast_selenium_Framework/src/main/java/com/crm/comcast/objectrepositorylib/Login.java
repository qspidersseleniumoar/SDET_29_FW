package com.crm.comcast.objectrepositorylib;
/**
 * 
 * @author Deepak
 *
 */

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.genericutility.WebDriverUtility;

public class Login  extends WebDriverUtility{   //R-1
	
	WebDriver driver;
	
   public Login(WebDriver driver) {             //R3
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
  }
	
	@FindBy(name = "user_name")          //R-2
	private WebElement userNameEdt; 
	
	@FindBy(name = "user_password")
	private WebElement passwordEdt; 
	
	
	@FindAll ({ @FindBy(id = "submitButton") , @FindBy(xpath = "//input[@type='submit']")})
	private WebElement loginBtn;


	public WebElement getUserNameEdt() {
		return userNameEdt;
	}


	public WebElement getPasswordEdt() {
		return passwordEdt;
	}


	public WebElement getLoginBtn() {
		return loginBtn;
	}

	/**
	 *   login to app
	 * @param userName
	 * @param password
	 */
	public void loginToApp(String userName , String password) {
	     waitForPageToLoad(driver);
        driver.manage().window().maximize();
        
		userNameEdt.sendKeys(userName);
		passwordEdt.sendKeys(password);
		loginBtn.click();
		
	}
	
	
	
}
