package utility;

import java.awt.Robot;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Utils
{
	public static WebDriver driver=null;
	private static WebElement found=null;
	private static String sMonth;
	private static String sDay;
	
	public static WebDriver openBrowser(int iTestCaseRow, String sTestName) throws Exception
	{
		//new ObjectMap(Constant.objectMap);
		Log.startTestCase(sTestName);
		String sBrowserName;
		String sURL;
		try{
			sBrowserName=ExcelConfig.getCellData(iTestCaseRow, Constant.col_Browser,  Constant.sheet_TestCases);
			sURL=ExcelConfig.getCellData(iTestCaseRow, Constant.col_URL,Constant.sheet_TestCases);
			if(sURL.equalsIgnoreCase("uat")){
				sURL=Constant.sURL_UAT;
			}else{
				sURL=Constant.sURL_QA;
			}
			ProfilesIni profile=new ProfilesIni();
			FirefoxProfile fp=profile.getProfile(Constant.ffProfile);
			//fp.setAcceptUntrustedCertificates(true);
			//fp.setAssumeUntrustedCertificateIssuer(false);
			DesiredCapabilities dc=null;
			
			if(sBrowserName.equalsIgnoreCase("firefox")){
				dc=DesiredCapabilities.firefox();
				dc.setBrowserName("firefox");
				//dc.setVersion("version");
				dc.setCapability(FirefoxDriver.PROFILE, fp);
				dc.setPlatform(Platform.WINDOWS);
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				
				ThreadLocal<WebDriver> driverStore=new ThreadLocal<WebDriver>();
				//JavaScriptErrorListener.addExtension(fp);
				System.setProperty("webdriver.gecko.driver", Constant.firefoxDriverPath);
				//driver=ThreadGuard.protect(new FirefoxDriver(dc));
				Log.info("New driver instantiated for "+sBrowserName.toUpperCase()+" browser");
				driverStore.set(driver);
			}else if(sBrowserName.equalsIgnoreCase("ie")){
				dc=DesiredCapabilities.internetExplorer();
				dc.setBrowserName("internet explorer");
				dc.setPlatform(Platform.WINDOWS);
				dc.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, sURL);
				dc.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				dc.setCapability("nativeEvents", false);
				dc.setCapability("unExpectedAlertBehaviour", "accept");
				dc.setCapability("disable-popup-blocking", true);
				dc.setCapability("ignoreProtectedModeSettings", true);
				dc.setCapability("ignoreZoomSeetings", true);
				dc.setCapability("requireWindowFocus", true);
				dc.setCapability("enablePersistentHover", false);
				dc.setCapability("enableNativeEvents", false);
				dc.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				dc.setCapability("IE.binary", "C:/Program Files (x86)/Internet Explorer/iexplore.exe");
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				dc.setJavascriptEnabled(true);
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				
				System.setProperty("webdriver.ie.driver", Constant.ieDriverPath);
				driver=new InternetExplorerDriver(dc);
				Log.info("New driver instantiated for "+sBrowserName.toUpperCase()+ "browser.");
			}else if(sBrowserName.equalsIgnoreCase("chrome")){
				dc=DesiredCapabilities.chrome();
				dc.setBrowserName("chrome");
				dc.setPlatform(Platform.WINDOWS);
				dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				ChromeOptions options=new ChromeOptions();
				options.addArguments("chrome.switches","-disable-extensions");
				
				System.setProperty("webdriver.chrome.driver", Constant.chromeDriverPath);
				driver=new ChromeDriver(options);
				Log.info("New Driver is instantiated for: "+sBrowserName.toUpperCase()+" browser.");
			}else if(sBrowserName.equalsIgnoreCase("chromeincognito")){
				System.setProperty("webdriver.chrome.driver",Constant.chromeincognitoDriverPath);
				dc=DesiredCapabilities.chrome();
				ChromeOptions options=new ChromeOptions();
				options.addArguments("incognito");
				dc.setCapability(ChromeOptions.CAPABILITY, options);
				driver=new ChromeDriver(dc);
				Log.info("New driver instantiated for "+sBrowserName.toUpperCase()+" browser.");
				driver.get("chrome://extensions-frame");
				WebElement checkbox=driver.findElement(By.xpath("//input[@type='checkbox']/ancestor::label[@class='incognito-control']"));
				if(!checkbox.isSelected()){
					checkbox.click();
				}
				Thread.sleep(1000);
			}
			else{
				throw new Exception("ERROR! Browser was not instantiated");
			}
			Robot r=new Robot();
			r.mouseMove(1, 1);
			//driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Constant.iImplicitWait, TimeUnit.SECONDS);
			Log.info("Implicit wait applied on the driver for "+Constant.iImplicitWait+" seconds");
			driver.get(sURL);
			Log.info("web application launched successfully. URL is "+sURL);
			return driver;
		}catch(Exception e){
			//Log.error("Class Utils | Method openBrowser | Exception desc:"+e.getMessage());
			Log.info("Class Utils | Method openBrowser | Exception desc:"+e.getMessage());
			throw(e);
		}
		
		
	}
	public static void startGrid(String browser, String url, String testname) throws MalformedURLException{
		Log.startTestCase(testname);
		try{
			ProfilesIni profile=new ProfilesIni();
			FirefoxProfile fp=profile.getProfile(Constant.ffProfile);
			DesiredCapabilities dc=null;
			if(browser.equalsIgnoreCase("firefox")){
				dc=DesiredCapabilities.firefox();
				dc.setBrowserName("firefox");
				//dc.setVersion("version");
				dc.setCapability(FirefoxDriver.PROFILE,fp);
				dc.setPlatform(Platform.WINDOWS);
			}else if(browser.equalsIgnoreCase("ie")){
				dc=DesiredCapabilities.internetExplorer();dc.setBrowserName("internetexplorer");
				dc.setPlatform(Platform.WINDOWS);
			}else if(browser.equalsIgnoreCase("chrome")){
				dc=DesiredCapabilities.chrome();
				dc.setBrowserName("chrome");
				dc.setPlatform(Platform.WINDOWS);
			}else{
				throw new Exception("ERROR! Browser was not instantiated");
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
			Robot r=new Robot();
			r.mouseMove(1, 1);
			driver.manage().window().maximize();
			Log.info("GRID is running using "+browser.toUpperCase()+" browser.");
			driver.get(url);
			Log.info("web application launched successfully. URL is "+url);
		}catch(Exception e){
			//Log.error("Class Utils | Method startGrid | Exception desc: "+e.getMessage());
			Log.info("Class Utils | Method startGrid | Exception desc: "+e.getMessage());
		}
	}
	public static void waitforElement(WebElement element, int iSeconds){
		WebDriverWait wait=new WebDriverWait(driver, iSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static void takeScreenShot(WebDriver driver, String testname) throws Exception{
		try{
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(Constant.screenshotsPath+testname+".png"));
		}catch(Exception e){
			//Log.error("Class Utils | Method takeScreenshot |Exception occurred while capturing screenshot: "+e.getMessage());
			Log.info("Class Utils | Method takeScreenshot |Exception occurred while capturing screenshot: "+e.getMessage());
			throw new Exception();
		}
	}
	public static int findAllFrames(WebDriver driver) throws Exception{
		try{
			List<WebElement> iframeElements=driver.findElements(By.tagName("iframe"));
			//Log.logConfig(Level.INFO, Constant.fluentWaitVerify, "Number of iframes on the page are: "+iframeElements.size(), null);
			Log.info("Number of iframes on the page are: ");
			return iframeElements.size();
		}catch(Exception e){
			//Log.error("Class Utils | Method findAllFrames | Exception occurred while locating all elements "+e.getMessage());
			Log.info("Class Utils | Method findAllFrames | Exception occurred while locating all elements "+e.getMessage());
			throw new Exception();
		}
	}
	public static void selectDropDown(WebElement element, String sDropDownValueToSelect) throws Exception{
		try{
			List<WebElement> options=element.findElements(By.tagName("option"));
			for(WebElement option: options){
				if(option.getText().equalsIgnoreCase(sDropDownValueToSelect)){
					option.click();
				}
			}
		}catch(Exception e){
			//Log.error("Class Utils | Method selectDropDownValue | Exception occurred while selecting dropdown value" +e.getMessage());
			Log.info("Class Utils | Method selectDropDownValue | Exception occurred while selecting dropdown value" +e.getMessage());
			throw new Exception();
		}
	}
	public static void scrollIntoView(WebDriver driver, WebElement element) throws Exception{
		try{
			JavascriptExecutor jse=(JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();", element);
		}catch(Exception e){
			//Log.error("Class Utils | Method scrollIntoView | Exception occurred while scrolling page: "+e.getMessage());
			Log.info("Class Utils | Method scrollIntoView | Exception occurred while scrolling page: "+e.getMessage());
			throw new Exception();
		}
	}
	public static void switchToIframe(String iframeId, String elementId, long maxWaitInMillis) throws Exception{
		long startTime=System.currentTimeMillis();
		WebElement iframe=new WebDriverWait(driver, (int)((maxWaitInMillis)/1000+1)).
				until(ExpectedConditions.visibilityOfElementLocated(By.xpath("iframe[@id='"+iframeId+"']")));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String script="var myIframe=document.getElementById('"+iframeId+"');"+
		"var myIframeDOC=myIfrane.contentDocument || myIframe.contentwindow.document;"+
				"var myElement=myIframeDOC.getElementId('"+elementId+"');"+"if(!myElement){return true;}else{return false;}";
		do{
			if((Boolean)js.executeScript(script)){
				driver.switchTo().frame(iframe);
			}
			Thread.sleep(1000);
		}while(System.currentTimeMillis()-startTime<maxWaitInMillis);
		throw new TimeoutException("TimeOut after"+(System.currentTimeMillis()-startTime)+"ms.Target was"+maxWaitInMillis+
				"ms.", new NoSuchElementException("could not locate element within Iframe. IframeId: "+iframeId+"ElementID:"+elementId+"."));
				
		
	}
	public static void submitIframe() throws Exception{
		driver.switchTo().defaultContent();
		Log.info("Switched to defaultcontent");
		try{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			String script="var iframe=document.getElementByTagName('iframe')[0];"+
			"var theFirstButton=document.evaluate('//button[1]',iframe.contentDocument,null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue;"+
					"theFirstButton.click();";
			js.executeScript(script);
			Thread.sleep(3000);
			Log.info("Click action is performed on submit create Account button");
			Reporter.log("Create New Account form filled up and subnmitted successfully");
		}catch(Exception e){
			//Log.error("Class Utils | Method submitIframe |Exception occurred while submitting the frame: "+e.getMessage());
			Log.info("Class Utils | Method submitIframe |Exception occurred while submitting the frame: "+e.getMessage());
			throw new Exception();
		}
	}
	public static boolean isElementPresent(WebDriver driver, By by){
		try{
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
	}
	public static WebElement getMatchedWebElement(List<WebElement> list, String sWorkItem) throws Exception{
		try{
			for(WebElement we:list){
				if(we.getText().split(" ")[0].equals(sWorkItem)){
					found=we;
					//Log.logConfig(Level.INFO, Constant.fluentWaitVerify, "Matched WebElement is fund based on "+sWorkItem, null);
					Log.info("Matched WebElement is fund based on "+sWorkItem);
					break;
				}
			}
		}catch(Exception e){
			Log.info("Matched WebElement is not found based on "+sWorkItem);
		}
		return found;
	}
	//List Dropdown
	public static void selectFromList(WebElement element, String listValueToSelect) throws Exception{
		try{
			List<WebElement> listOfElements=element.findElements(By.tagName("li"));
			for(WebElement option: listOfElements){
				if(option.getText().equalsIgnoreCase(listValueToSelect)){
					option.click();
				}
			}
		}catch(Exception e){
			//Log.error("Class Utils | Method selectFromList | Exception Occurred while selecting from list: "+e.getMessage());
			Log.info("Class Utils | Method selectFromList | Exception Occurred while selecting from list: "+e.getMessage());
			throw new Exception();
		}
	}
	public static Date incrementOfDate(String sDate, int iMonths) throws Exception{
		DateFormat formatter=new SimpleDateFormat("MM/dd/yyyy");
		Date date=(Date)formatter.parse(sDate);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, iMonths);
		return cal.getTime();
	}
	//Todays's date
	public static String getCurrentDate() throws Exception{
		DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
		Date date=new Date();
		String date1=dateFormat.format(date);
		return date1;
	}
	//Random Date Generation
	@SuppressWarnings({"static-access"})
	public static String getRandomDate() throws Exception{
		GregorianCalendar gc= new GregorianCalendar();
		int year=randBetween(2016,2016);
		gc.set(gc.YEAR, year);
		int dayOfYear=randBetween(1,gc.getActualMaximum(gc.DAY_OF_YEAR));
		gc.set(gc.DAY_OF_YEAR, dayOfYear);
		int month=(gc.get(gc.MONTH)+1);
		sMonth=String.format("%2d", month);
		int dayOfMonth=gc.get(gc.DAY_OF_MONTH);
		sDay=String.format("%02d", dayOfMonth);
		String date=(sMonth+"/"+sDay+"/"+gc.get(gc.YEAR));
		return date;
	}
	public static int randBetween(int start, int end){
		return start+(int)Math.round(Math.random()+(end-start));
	}
	public static void compareAndActualExpected(String sActualValue, String sExpectedValue, String sMsgPass, String sMsgFail) throws Exception{
		if(sActualValue.equalsIgnoreCase(sExpectedValue)){
			Log.info(sMsgPass);
		}else{
			//Log.error("TestCaseFailed..."+sMsgFail);
			Log.info("TestCaseFailed..."+sMsgFail);
			throw new Exception();
		}
	}
			
	public static void closeBrowser()
	{
		driver.close();
		System.out.println("Browser is closed");
	}

}

