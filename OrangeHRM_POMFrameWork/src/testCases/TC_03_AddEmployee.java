package testCases;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import appModules.Login_Action;
import appModules.Logout_Action;
import appModules.Pim_Action;
import utility.Constant;
import utility.Utils;

public class TC_03_AddEmployee
{/*      
       @BeforeClass	
	    public static void openBrowser()throws Exception
	    {
    	   Utils.openBrowser(Constant.sUrl, Constant.sBrowserPath);
    	   Utils.maximize();
	    }
        @BeforeMethod()
        public static void login()throws Exception
        {
        	Login_Action.login_Execute();
        }
        
        @Parameters({"sFirstName","sLastName","sEmpId"})
        @Test
        public static void test(String sFirstName,String sLastName, String sEmpId) throws Exception
        {	
        	Reporter.log("AddEmployee test()method successfully started",true);
        	Pim_Action.pimAction_Execute(sFirstName,sLastName,sEmpId);
        	Reporter.log("AddEmployee test()method successfully completed",true);
        }
        @AfterMethod
        public static void logout()throws Exception
        {
            	
        	Logout_Action.logout_Execute();
	    }
        @AfterClass
        public static void closeBrowser()throws Exception
        {
    	   Utils.closeBrowser();
        }
*/}
