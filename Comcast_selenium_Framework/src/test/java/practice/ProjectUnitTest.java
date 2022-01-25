package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class ProjectUnitTest {
	@Test
	public void pojectUnitTest() throws Throwable {	
		String ProjectName = "Apple";
		Connection conn = null;
		try {
				Driver driverRef = new Driver();
				/* step 1 : load /register *mysql *the databade */
				  DriverManager.registerDriver(driverRef);
				/* step 2 : connect to db*/
				   conn=  DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
				  System.out.println("connection is done");
				/* step 3 : create query statement*/
					Statement stat=	 conn.createStatement();
				  String query = "select * from project";
				  
				/* step 4 : execute the Query*/
				    ResultSet resultset =  stat.executeQuery(query);
				    boolean flag = false;
				    while (resultset.next()) {
				    	String actProjectName = resultset.getString(4);
				    	if(actProjectName.equals(ProjectName)) {
				    		flag= true;
				    	}
 					}
				
				    Assert.assertTrue(flag);
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			/*step 5: close the connection */
		    conn.close();
		    System.out.println("============close db connection=========");
		}    
				    


		
		
	}
}
