package utility;

import java.lang.reflect.Method;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DriverScript {
	
	public static Method[] method;
	public static int iTestCase;
	public static int iTestLastCase;
	public static String sTestName;
	public static String sTestID;
	public static String sRunMode;
	static ExtentReports report;
	static ExtentTest logger;
	
	public DriverScript() throws NoSuchMethodException, SecurityException{
		
	}
	
	public static void main(String args[]) throws Exception{
		
		report=new ExtentReports(Constant.extentReportsPath+"Regression_COB_SUITE.html", true);
		ExcelConfig.setExcelFile(Constant.path_TestData+Constant.file_TestData);
		DriverScript startEngine=new DriverScript();
		startEngine.execute_TestCase();
		report.flush();
		report.close();
	}
	public void execute_TestCase() throws Exception{
		int iTotalTestCases=ExcelConfig.getRowUsed(Constant.sheet_TestCases);
		int i=1;
		for(int iTestCase=1;iTestCase<iTotalTestCases;iTestCase++){
			sTestName=ExcelConfig.getCellData(iTestCase, Constant.col_TestCaseName, Constant.sheet_TestCases);
			Class<?> className=Class.forName("testcases"+sTestName);
			sTestID=ExcelConfig.getCellData(iTestCase, Constant.col_TestID, Constant.sheet_TestCases);
			sRunMode=ExcelConfig.getCellData(iTestCase, Constant.col_RunMode, Constant.sheet_TestCases);
			
			if(sRunMode.equals("Yes")){
				iTestCase=ExcelConfig.getRowContains(sTestID, Constant.col_TestID, Constant.sheet_TestCases);
				logger=report.startTest(iTestCase+"-"+sTestName+"-"+sTestID);

				Log.info("Running TestCaseNumber: " +iTestCase);
				logger.log(LogStatus.INFO, "Runnning TestCase Number: "+iTestCase);
				execute_Actions(className);
				String testResult=ExcelConfig.getCellData(iTestCase, Constant.col_Result, Constant.sheet_TestCases);
				
				if(testResult.equalsIgnoreCase("Fail") && i<=Constant.iRetryCount){
					//Log.error("TestCase Failed. Retrying the test count == " +i);
					Log.info("TestCase Failed. Retrying the test count == " +i);
					iTestCase--;
					i++;
				}
				if(testResult.equalsIgnoreCase("Fail")){
					logger.log(LogStatus.FAIL, "TestCase Failed. An Error occurred during testcase execution");
					logger.addScreenCapture(Constant.screenshotsPath+sTestName+"-"+sTestID+"_Error.png");
				}else{
					logger.log(LogStatus.INFO, "TestCase Executed Successfully");
				}
				logger.log(LogStatus.INFO, "Follow the link to see complete Error. <a Ref=c:/git/OrangeHRM");
			
				report.endTest(logger);
			}
		}
	}
	
	private void execute_Actions(Class<?> className) throws Exception{
		method=className.getMethods();
		try{
			for(int i=0;i<method.length;i++){
				if(method[i].getName().equalsIgnoreCase("before")){
					method[i].invoke(className.newInstance(), sTestID);
				}
			}
		}
		catch(Exception e){
			//Log.error(e.getMessage());
			Log.info(e.getMessage());
		}
		
		try{
			for(int j=0;j<method.length;j++){
				if(method[j].getName().equalsIgnoreCase("main")){
					method[j].invoke(className.newInstance(), (Object[])null);
				}
			}
		}
		catch(Exception e){
			//Log.error(e.getMessage());
			Log.info(e.getMessage());
		}
		
		try{
			for(int e=0;e<method.length;e++){
				if(method[e].getName().equalsIgnoreCase("after")){
					method[e].invoke(className.newInstance(), (Object[])null);
				}
			}
		}
		catch(Exception e){
			//Log.error(e.getMessage());
			Log.info(e.getMessage());
		}
	}
}
