package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
		/*step 1: get the java representation object of the physical file*/
		FileInputStream fis = new FileInputStream("C:\\Users\\Deepak\\Desktop\\commonData.properties");
       /*step 2 : load all the Keys using PRoperties class*/
		Properties pobj = new Properties();
		pobj.load(fis);
		/*step 3 : read the data from File using getProperty("key")*/
		String data = pobj.getProperty("url");
				System.out.println(data);
		
	}

}
