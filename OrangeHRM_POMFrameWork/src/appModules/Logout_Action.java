package appModules;
import org.openqa.selenium.WebDriver;
import pageObjects.LogoutPage;
import utility.Log;
import pageObjects.BaseClass;


public class Logout_Action extends BaseClass
{
	public Logout_Action(WebDriver driver)
	{
		super(driver);
	}
	public static void logout_Execute()throws Exception
	{
		LogoutPage.lnk_WelcomeText().click();
		Log.info("Click action is performed on WelcomeText link");
		LogoutPage.lnk_Logout().click();
		Log.info("Click action is performed on Logout link");
	}
}