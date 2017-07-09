package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utility.Log;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseClass
{
	public static WebElement element;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
    }
	
   //UserName textbox
    public static WebElement txtbx_UserName()throws Exception{
    	element=null;
    	try{
    			element=driver.findElement(By.id("txtUsername"));
    			Log.info("UserName text-box is found on Login page");
    		  
    	  	}catch(Exception e){
    	  		Log.info("UserName text-box is not found on Login page");
    	  		throw(e);
    	  	}
    	  return element;
      }
  //PassWord textbox
    public static WebElement txtbx_password()throws Exception{
    	element=null;
    	try{
    			element=driver.findElement(By.id("txtPassword"));
    			Log.info("Password text-box is found on Login page");
    		  
    	  	}catch(Exception e){
    	  		Log.info("Password text-box is not found on Login page");
    	  		throw(e);
    	  	}
    	return element;
      }
      
    //Submit button
     public static WebElement btn_Login()throws Exception{
    	element=null;
    	try{
    			element=driver.findElement(By.name("Submit"));
    			Log.info("Submit Button is found on Login page");
    	   
           	}catch(Exception e){
           		Log.info("Submit Button is not found on Login page");
           		throw(e);
    	   }
    	return element;
       }
   }