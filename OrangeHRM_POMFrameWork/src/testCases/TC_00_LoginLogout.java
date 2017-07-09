package testCases;

import utility.Constant;
import utility.ExcelConfig;
import utility.Log;
import utility.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import org.testng.annotations.BeforeMethod;

import appModules.Login_Action;
import appModules.Logout_Action;
import pageObjects.BaseClass;

public class TC_00_LoginLogout
{
	static ExtentReports report;
	static ExtentTest logger;
	static WebDriver driver;
	private static int iTestData;
	private static int iTestCase;
	private static String sBrowser;
	private static String sTestName;
	
	@Parameters({"sTestID"})
	@BeforeClass
	public static void before(@Optional(Constant.sTest_ID) String sTestID) throws Exception{
		sTestName=Thread.currentThread().getStackTrace()[1].getClassName().substring(Thread.currentThread().getStackTrace()[1].getClassName().indexOf('.')+1)+"_"+sTestID;
		report=new ExtentReports(Constant.extentReportsPath+sTestName+".html", true);
		logger=report.startTest(sTestName);
		ExcelConfig.setExcelFile(Constant.path_TestData+Constant.file_TestData);
		iTestCase=ExcelConfig.getRowContains(sTestID, Constant.col_TestID, Constant.sheet_TestCases);
		iTestData=ExcelConfig.getRowContains(sTestID, Constant.col_TestID, Constant.sheet_TestData_Login);
		sBrowser=ExcelConfig.getCellData(iTestCase, Constant.col_Browser, Constant.sheet_TestCases);
		driver=Utils.openBrowser(iTestCase, sTestName);
		logger.log(LogStatus.INFO, "SELENIUM is running using "+sBrowser.toUpperCase()+" browser. Test URL is: "+Constant.sURL_QA);
		new BaseClass(driver);
		
	}
	@Test
	public static void test() throws Exception
	{
		try{
			Login_Action.login_Execute(iTestData, Constant.sheet_TestData_Login);
			Utils.takeScreenShot(driver, sTestName+"_Login");
			logger.log(LogStatus.INFO, "Login action is succesfully completed. "+logger.addScreenCapture(Constant.screenshotsPath+sTestName+"_Login.png"));
			
			Thread.sleep(Constant.iThreadWaitLong);
			
			Logout_Action.logout_Execute();
			Utils.takeScreenShot(driver, sTestName+"_Logout");
			logger.log(LogStatus.INFO, "Logout action is succesfully completed. "+logger.addScreenCapture(Constant.screenshotsPath+sTestName+"_Logout.png"));
			
		}catch(Exception e){
			BaseClass.bResult=false;
			ExcelConfig.setCellData("Fail", iTestCase, Constant.col_Result, Constant.sheet_TestCases);
			Utils.takeScreenShot(driver, sTestName+"_Error");
			logger.log(LogStatus.FAIL, "TestCase is failed. An Error Occurred during test case execution"+logger.addScreenCapture(Constant.screenshotsPath+sTestName+"_Error.png"));
			throw new Exception();
		}
		if(BaseClass.bResult){
			ExcelConfig.setCellData("Pass", iTestCase, Constant.col_Result, Constant.sheet_TestCases);
			logger.log(LogStatus.PASS, "TestCase is passed. ");
		}
		
			
	}
	@AfterClass
	public static void after() throws Exception{
		report.endTest(logger);
		report.flush();
		Utils.closeBrowser();
		Log.endTestCase();
	}

}
