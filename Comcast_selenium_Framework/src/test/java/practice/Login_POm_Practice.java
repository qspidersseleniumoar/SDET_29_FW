package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.objectrepositorylib.Login;

public class Login_POm_Practice {
	
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		   driver.get("http://localhost:8888");  
		
		
    Login lp = new Login(driver);
    
    lp.loginToApp("admin", "manager");

	}

}
