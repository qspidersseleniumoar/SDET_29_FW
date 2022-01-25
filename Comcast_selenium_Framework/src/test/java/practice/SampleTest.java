package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SampleTest {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis = new FileInputStream("./data/commonData.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		//String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url");
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		WebDriver driver = null;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the BROWSER---");
		String BROWSER = input.next();
		
		
		
		/*step 1 : Login*/
	     if(BROWSER.equals("chrome")) {
	    	  driver = new ChromeDriver();
	     }else if(BROWSER.equals("firefox")) {
	    	  driver = new FirefoxDriver();
	     }else if(BROWSER.equals("ie")) {
	    	  driver = new InternetExplorerDriver();
	     }
		driver.get(URL);	
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

	}

}