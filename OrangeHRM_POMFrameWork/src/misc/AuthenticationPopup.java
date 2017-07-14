package misc;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import utility.Log;

public class AuthenticationPopup {
	
	public static void authenticatePopup_Execute() throws Exception{
		Thread.sleep(5000);
		Robot rb=new Robot();
		StringSelection sUserName=new StringSelection("userName");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sUserName, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		
		//tab to password entry field
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		
		//Enter password by ctrl+v
		StringSelection sPassword=new StringSelection("password");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sPassword, null);
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_V);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		
		//Press after
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		Log.info("Successfully executed the authentication popup window.");
	}

}
