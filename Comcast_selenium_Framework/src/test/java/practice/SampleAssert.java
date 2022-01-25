package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SampleAssert {
	
	@Test
	public void test1() {
		SoftAssert soft = new SoftAssert();
		
		System.out.println("step-1");
		System.out.println("step-2");
		soft.assertEquals("A" , "B");
		System.out.println("step-3");
		soft.assertEquals("B" , "C");
		System.out.println("step-4");
		System.out.println("=====step-5==========");
		
		soft.assertAll();
	}
	
	
	@Test
	public void test2() {
		System.out.println("step-1");
		System.out.println("step-2");
		Assert.assertEquals("A" , "A");
		System.out.println("step-3");
		System.out.println("step-4");
		System.out.println("=====step-5==========");
	}

}
