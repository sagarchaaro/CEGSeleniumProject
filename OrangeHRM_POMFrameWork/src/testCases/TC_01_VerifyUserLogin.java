package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility.Constant;

public class TC_01_VerifyUserLogin {
	
	@Parameters({"sTestID"})
	@BeforeClass
	public void before(@Optional (Constant.sTest_ID) String sTestID){
		int a=1;
		int b=2;
		int c=a+b;
		System.out.println(sTestID);
		System.out.println("Welcome");
	}
	@Test
	public static void test(){
		System.out.println("welcome1");
	}
	

}
