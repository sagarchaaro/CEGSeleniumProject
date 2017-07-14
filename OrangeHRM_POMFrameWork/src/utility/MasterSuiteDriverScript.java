package utility;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.apache.maven.shared.utils.io.FileUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class MasterSuiteDriverScript {
	
	public static Method[] method;
	public static int iTestCase;
	public static int iTestLastCase;
	public static String sTestName;
	public static String iTestNumber;
	public static String sTestID;
	public static String sRunMode;
	static ExtentReports report;
	static ExtentTest logger;
	
	public static int count=0,pos=0;
	public static int[] TestPosition=new int[10];
	public static boolean isSkipfalse;
	
	public MasterSuiteDriverScript() throws NoSuchMethodException, SecurityException{
		
	}
	public static void main(String args[]) throws Exception{
		PropertyConfigurator.configure("src/test/resources/log4j.properties");
		DOMConfigurator.configure("src/test/resources/log4j.xml");
		ExcelConfig.setExcelFile(Constant.path_TestData+Constant.file_TestData);
		MasterSuiteDriverScript startEngine=new MasterSuiteDriverScript();
		startEngine.execute_TestCase();
	}
	public void execute_TestCase() throws Exception{
		int iTotalTestCases=ExcelConfig.getRowUsed(Constant.sheet_TestCases);
		XmlSuite suite=new XmlSuite();
		try{
			FileUtils.deleteDirectory(new File(Constant.tngsuitereportPath));
			File file=new File(Constant.testresourcesPath+"MasterSuite.xml");
			file.delete();
			Log.info("Test results for previous cycle were removed from the directory");
			//Log.warn("Test results for previous cycle were removed from the directory");
		}catch(Exception e){
			Log.info("No Previous test results are available. "+e);
			//Log.error("No Previous test results are available. "+e);
		}
		for(int iTestCase=1;iTestCase<=iTotalTestCases;iTestCase++){
			sTestName=ExcelConfig.getCellData(iTestCase, Constant.col_TestCaseName, Constant.sheet_TestCases);
			sTestID=ExcelConfig.getCellData(iTestCase, Constant.col_TestID, Constant.sheet_TestCases);
			iTestNumber=ExcelConfig.getCellData(iTestCase, Constant.col_TestCaseNumber, Constant.sheet_TestCases);
			sRunMode=ExcelConfig.getCellData(iTestCase, Constant.col_RunMode,Constant.sheet_TestCases);
			if(sRunMode.equalsIgnoreCase("Yes")){
				iTestCase=ExcelConfig.getRowContains(sTestID, Constant.col_TestID, Constant.sheet_TestCases);
				suite.setName("OrangeHRM_Regression_Suite");
				TestListenerAdapter tla=new TestListenerAdapter();
				List<String> listenerClasses=new ArrayList<String>();
				listenerClasses.add("utility.TestIMethodInterCeptorListener");
				suite.setListeners(listenerClasses);
				XmlTest test=new XmlTest(suite);
				test.setName(sTestName+"-"+sTestID);
				Map<String, String> parameters=new HashMap<String, String>();
				parameters.put("sTestID",sTestID);
				test.setParameters(parameters);
				List<XmlClass> classes=new ArrayList<XmlClass>();
				classes.add(new XmlClass("testcases."+sTestName));
				test.setXmlClasses(classes);
			}
		}
		FileWriter writer=new FileWriter(new File(Constant.testresourcesPath,"MasterSuite.xml"));
		writer.write(suite.toXml());
		writer.flush();
		writer.close();
	}

}
