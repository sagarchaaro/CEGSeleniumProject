package appModules;
import org.openqa.selenium.WebDriver;
import pageObjects.BaseClass;
import pageObjects.LoginPage;
import utility.Constant;
import utility.ExcelConfig;
import utility.Log;

public class Login_Action extends BaseClass
{ 
	public Login_Action(WebDriver driver)
	{
      super(driver); 
    }
    public static void login_Execute(int iTestCaseRow, String sSheetName)throws Exception
    {
    	String sUserName=ExcelConfig.getCellData(iTestCaseRow, Constant.col_UserName, sSheetName);
    	LoginPage.txtbx_UserName().sendKeys(sUserName);
    	Log.info(sUserName+" is entered in UserName text-box");
    	String sPassword=ExcelConfig.getCellData(iTestCaseRow, Constant.col_Password, sSheetName);
    	LoginPage.txtbx_password().sendKeys(sPassword);
    	Log.info(sPassword+"is entered in Password text-box");
        LoginPage.btn_Login().click();
        Log.info("Click action is performed on Login button");
     
    }
        
}
