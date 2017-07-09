package utility;

public class Constant 
{
	
	public static String sURL_UAT="http://opensource.demo.orangehrmlive.com/";
	public static String sURL_QA="http://opensource.demo.orangehrmlive.com/";
	public static String ffProfile="";
	public static String sBrowserPath="";
	public static String firefoxDriverPath="";
	public static String ieDriverPath="";
	public static String chromeDriverPath="D:\\Java and Selenium\\Selenium\\Jars and Drivers\\Drivers\\chromedriver\\chromedriver.exe";
	public static String chromeincognitoDriverPath="";
	public static String safariDriverPath="";
	
	static String filePath=System.getProperty("user.dir");
	public static String screenshotsPath=filePath+"\\Screenshots\\";
	public static String extentReportsPath=filePath+"\\ExtentReports\\";
	public static String windowFilesPath=filePath+"\\WindowsFiles\\";
	public static String AutoItFile="FileUpload.exe";
	public static String path_TestData=filePath+"\\TestData\\";
	public static String file_TestData="OrangeHRMTestData.xlsx";
	public static String sheet_TestCases="TestCases";
	public static String sheet_TestData_Login="Login";
	public static String sheet_TestData_AddEmployee="Add Employee";
	public static final String sTest_ID="TC_03_01";
	public static String objectMap="";
	
	
	//TimeOuts and Waits
	public static int iImplicitWaitShort=5;
	public static int iImplicitWait=20;
	public static int iImplicitWaitlong=40;
	public static long fluentWaitVerify=18000;
	public static int iThreadWaitShort=1000;
	public static int iThreadWait=3000;
	public static int iThreadWaitLong=5000;
	
	public static int iRetryCount=5;
	
	//TestCase Sheet
	public static int col_TestCaseName=1;
	public static int col_TestID=2;
	public static int col_Browser=4;
	public static int col_URL=5;
	public static int col_Result=6;
	public static int col_RunMode=7;
	public static int col_Comments=8;
	
	//Login TestData Sheet
	public static int col_UserName=3;
	public static int col_Password=4;
	
	//AddEmployee TestData Sheet
	public static int col_FirstName=5;
	public static int col_MiddleName=6;
	public static int col_LastName=7;
	
			

}
