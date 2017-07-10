package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

public class Pim_Page extends BaseClass
{
	public static WebElement element;
	public Pim_Page(WebDriver driver) {
		super(driver);
	}

	public static WebElement lnk_PimMenu()throws Exception{
		element=null;
		try{
			element=driver.findElement(By.xpath("//*[@id='menu_pim_viewPimModule']/b"));
			Log.info("PIM menu is found on Home page");
		}catch(Exception e){
			Log.info("PIM menu is not found on Home page");
			throw(e);
		}
		return element;	  
	}
  
	public static WebElement lnk_AddEmployee()throws Exception{
		element=null;
		try{ 
			element=driver.findElement(By.xpath("//*[@id='menu_pim_addEmployee']"));
			Log.info("Add Employee sublink is found on Home page");
		}catch(Exception e){
			Log.info("Add Employee sublink is found on Home page");
			throw(e);
		}
		return element;
	 }
  
	public static WebElement txtbx_FirstName()throws Exception
	{
		element=null;
		try{
			element=driver.findElement(By.xpath("//*[@id='firstName']"));
			Log.info("First Name text-box is found on Add Employee page");
		}catch(Exception e){
			System.err.println("First Name text-box is not found on Add Employee page");
			throw(e);
		}
	  return element;
	}
	public static WebElement txtbx_LastName()throws Exception{
		element=null;
		try{
			element=driver.findElement(By.xpath("//*[@id='lastName']"));
			Log.info("Last Name text-box is found on Add Employee page");
		}catch(Exception e){
			Log.info("Last Name text-box is found on Add Employee page");
			throw(e);
		}
		return element;
	}
	public static WebElement txtbx_Empid()throws Exception{
		element=null;
		try{
			element=driver.findElement(By.xpath("//*[@id='employeeId']"));
			System.out.println("Employee Id is found on Add Employee page");
		}catch(Exception e){
			System.err.println("Employee Id is not found on Add Employee page");
			throw(e);
		}
	  return element;
	}
	public static WebElement btn_save()throws Exception{
		element=null;
		try
		{
			element=driver.findElement(By.xpath("//*[@id='btnSave']"));
			System.out.println("Save button is found on Add Employee page");
		}catch(Exception e){
			System.err.println("Save button is not found on Add Employee page");
			throw(e);
		}
	  return element;

	}
    public static WebElement lnk_EmployeeList() throws Exception{
    	element=null;
  	  	try{
  	  		element=driver.findElement(By.xpath("//*[@id='menu_pim_viewEmployeeList']"));
  	  		System.err.println("Employee List link is found on Home page");
  	  	}catch(Exception e){
  	  		System.err.println(" Employee List link is not found on Home page");
  	  		throw(e);
  	  	}
  	  	return element;
    }
    public static WebElement checkbox_SelectRecord() throws Exception{
    	element=null;
   	  	try{
   	  		element=driver.findElement(By.xpath("//*[@id='ohrmList_chkSelectRecord_17']"));
   	  		System.err.println("Select Record check-box is found on Employee List page");
   	  	}catch(Exception e){
   	  		System.err.println("Select Record check-box is found on Employee List page");
   	  		throw(e);
   	  	}
   	  	return element;
    }
    public static WebElement btn_Delete() throws Exception{
     	element=null;
   	  	try{
   	  		element=driver.findElement(By.xpath("//*[@id='btnDelete']"));
   	  		System.err.println("Delete button is found on Employee List page");
   	  	}catch(Exception e){
   	  		System.err.println("Delete button is not found on Employee List page");
   	  		throw(e);
   	  	}
   	  return element;
    }

    public static WebElement btn_OkPopup() throws Exception
    {
     	element=null;
   	  	try{
   	  		element=driver.findElement(By.xpath("//*[@id='dialogDeleteBtn']"));
   	  		System.out.println("Ok button is found on Alert pop-up");
   	  	}catch(Exception e){
   	  		System.err.println("Ok button is not found on Alert pop-up");
   	  		throw(e);
   	  	}
   	  	return element;
    }    
    public static WebElement btn_ChooseFile() throws Exception
    {
     	element=null;
   	  	try{
   	  		element=driver.findElement(By.xpath("//*[@id='photofile']"));
   	  		Log.info("ChooseFile button is found on Add Employee Page");
   	  	}catch(Exception e){
   	  		Log.info("ChooseFile is not found on Add Employee Page");
   	  		throw(e);
   	  	}
   	  	return element;
    }
    
}


