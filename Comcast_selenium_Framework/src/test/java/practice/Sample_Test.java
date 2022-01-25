package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.ExcelUtility;
import com.crm.comcast.genericutility.JavaUtlity;

public class Sample_Test {
   @Test(dataProvider = "dp_addToCartAndBill")
	private  void addToCartAndBill(String pName , String qty) {
	   
      System.out.println("execute "+pName+" att to cart & bill");
	
	}
   
   @DataProvider
   public Object[][] dp_addToCartAndBill() throws Throwable {
	   ExcelUtility elib = new ExcelUtility();
	   int rowCount = elib.getRowCount("addToCart");
	   
	   Object[][] objArr = new Object[rowCount][2];
	   
	   for(int i=0; i<rowCount; i++) {
	   
	       objArr[i][0] = elib.getDataFromExcel("addToCart", i, 0);
	       objArr[i][1] = elib.getDataFromExcel("addToCart", i, 1);
	   } 	
return objArr;   
   }
   
   
  

}



