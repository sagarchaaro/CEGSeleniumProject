package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
			System.out.println("Welcome text link is found on Home page"); 
		}catch(Exception e){
			System.err.println("Welcome text not found on Home page");
			throw(e);
		}
		return element;
	}
	
	public static WebElement lnk_Logout()throws Exception{
	   element=null;
	   try{
		   	element=driver.findElement(By.linkText("Logout"));
		   	System.out.println("logout button is found on Home page");
		}catch(Exception e){
			System.err.println("Logout button is not found on Home page");
			throw(e);
		}
	   return element;
	}
  }