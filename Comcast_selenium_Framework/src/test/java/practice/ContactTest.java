package practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.JavaUtlity;

public class ContactTest {

	
	@BeforeClass
	public void configBC() {
		  System.out.println("=======launch the BROWSER=========");
	}
	
	@BeforeMethod
	public void configBM() {
	      System.out.println("==step 1 : login==");
	}

   
   @Test
	private  void createContactTest() {
      System.out.println("step 2 : navigate to conatct Page");
      System.out.println("step 3 : create a new Conatct");
      System.out.println("step 4 : ------verify--------");
   }
   
   @Test
	private  void modifyContactTest() {
	      System.out.println("step 2 : navigate to conatct Page");
	      System.out.println("step 3 : create a new Conatct & modify ");
	      System.out.println("step 4 : verify ");
   }
   
	@AfterMethod
	public void configAM() {
	      System.out.println("===step 5 : logout===");
	}
	@AfterClass
	public void configAC() {
		  System.out.println("=======close the BROWSER=========");
	}

}



