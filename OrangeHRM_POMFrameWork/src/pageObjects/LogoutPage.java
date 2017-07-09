package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;

import org.openqa.selenium.By;


public class LogoutPage extends BaseClass{	
	
	public static WebElement element;
	public LogoutPage(WebDriver driver){
		  super(driver);
      }
	
	//Welcome text
	public static WebElement lnk_WelcomeText()throws Exception{
		element=null;
		try{
			element=driver.findElement(By.id("welcome"));
			Log.info("Welcome text link is found on Home page"); 
		}catch(Exception e){
			Log.info("Welcome text not found on Home page");
			throw(e);
		}
		return element;
	}
	
	public static WebElement lnk_Logout()throws Exception{
	   element=null;
	   try{
		   	element=driver.findElement(By.linkText("Logout"));
		   	Log.info("logout button is found on Home page");
		}catch(Exception e){
			Log.info("Logout button is not found on Home page");
			throw(e);
		}
	   return element;
	}
  }