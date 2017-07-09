 package appModules;

import org.openqa.selenium.WebDriver;
import pageObjects.Pim_Page;
import utility.Constant;
import utility.ExcelConfig;
import utility.Log;
import pageObjects.BaseClass;

public class Pim_Action extends BaseClass
{
	public Pim_Action(WebDriver driver)
	{
		super(driver); 
	}

    public static  void pimAction_Execute(int iTestCaseRow, String sSheetName) throws Exception
    {
    	
        Pim_Page.lnk_PimMenu().click();
        Log.info("Click action is performed on PIM Link");
        Thread.sleep(Constant.iThreadWait);
        Pim_Page.lnk_AddEmployee().click();
        Log.info("Click action is performed on Add Employee sub-Link");
        String sFirstName=ExcelConfig.getCellData(iTestCaseRow, Constant.col_FirstName, sSheetName);
        Pim_Page.txtbx_FirstName().sendKeys(sFirstName);
        Log.info(sFirstName +" is entered in FirstName text-box");
        String sLastName=ExcelConfig.getCellData(iTestCaseRow, Constant.col_LastName, sSheetName);
        Pim_Page.txtbx_LastName().sendKeys(sLastName);
        Log.info(sFirstName +" is entered in LastName text-box");
        /*Pim_Page.txtbx_Empid().clear();
        Pim_Page.txtbx_Empid().sendKeys(eId);
        System.out.println("Employee id is Entered");*/
        Pim_Page.btn_ChooseFile().click();
        Log.info("Click action is performed on ChooseFile button");
        Runtime.getRuntime().exec(Constant.windowFilesPath+Constant.AutoItFile);
        Log.info("File Upload action is performed");
        Thread.sleep(5000);
        Pim_Page.btn_save().click();
        Log.info("Click Action is performed on Save button");
    }
    public static void pim_deleteAction() throws Exception
    {
        System.out.println("Iam in DElete Action class");
        Thread.sleep(3000);
        Pim_Page.lnk_PimMenu().click();
        System.out.println("Pim Menu is clicked");
        Thread.sleep(3000);
        Pim_Page.lnk_EmployeeList().click();
        System.out.println("Viem employee list is clicked");
        Pim_Page.checkbox_SelectRecord().click();
        System.out.println("List is Present in the page");
        Pim_Page.btn_Delete().click();
        System.out.println("Delete buton is clicked");
        Pim_Page.btn_OkPopup().click();
        System.out.println("Deleted Succesfully");   	
     }
}
