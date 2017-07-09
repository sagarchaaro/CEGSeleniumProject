 package appModules;

import org.openqa.selenium.WebDriver;
import pageObjects.Pim_Page;
import pageObjects.BaseClass;

public class Pim_Action extends BaseClass
{
	public Pim_Action(WebDriver driver)
	{
		super(driver); 
	}

    public static  void pimAction_Execute(String fname,String lastName,String eId) throws Exception
    {
    	System.out.println("Add Employee Details");
        Pim_Page.lnk_PimMenu().click();
        System.out.println("Pim Menu is clicked");
        Thread.sleep(3000);
        System.out.println("AddEmloyee Details");
        Pim_Page.lnk_AddEmployee().click();
        System.out.println("Add employee is clicked");
        Pim_Page.txtbx_FirstName().sendKeys(fname);
        System.out.println("First name is entered");
        Pim_Page.txtbx_LastName().sendKeys(lastName);
        System.out.println("Last name is entered");
        Pim_Page.txtbx_Empid().clear();
        Pim_Page.txtbx_Empid().sendKeys(eId);
        System.out.println("Employee id is Entered");
        Pim_Page.btn_save().click();
        System.out.println("Save button is clicked");
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
