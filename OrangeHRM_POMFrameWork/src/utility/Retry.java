package utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import appModules.Logout_Action;
import pageObjects.BaseClass;

public class Retry {
	WebDriver driver;
	int minRetryCount=0;
	public boolean retry(ITestResult result){
		if(minRetryCount<Constant.iRetryCount){
			//Log.error("Following test is failing ==="+result.getName());
			Log.info("Following test is failing ==="+result.getName());
			Log.info("Retrying the test. count is=="+(minRetryCount+1));
			try{
				driver=BaseClass.driver;
				driver.get(Constant.sURL_QA);
				Log.info("Web application is launched successfully. URL is: "+Constant.sURL_QA);
				Logout_Action.logout_Execute();
				minRetryCount++;
				return true;
			}catch(Exception e){
				Log.info("Exception "+e.getMessage());
				//Log.error("Exception "+e.getMessage());
				minRetryCount++;
				return true;
			}
		}
		return false;
	}

}
