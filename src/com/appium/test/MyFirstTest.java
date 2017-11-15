package com.appium.test;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import junit.framework.Assert;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities; //Used to set device properties:device name, platform, version, app package name, activity name, etc. It has various methods eg. setCapability()-> used to set the device name, platform version, platform name, absolute path of the app under test (the .apk file of the app(Android) under test), app Activity (in Android) and appPackage(java).
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest; //@AfterTest annotated method will be executed when all @Test annotated methods completed its execution
import org.testng.annotations.BeforeTest; //@Before Test annotated method will be executed before any @Test method
import org.testng.annotations.Test; //@Test annotation describes method as a test method or part of your test

public class MyFirstTest {
	
	//Running Code
	AndroidDriver driver;
	String destDir;
	DateFormat dateFormat;
	String rootDirectory = System.getProperty("user.dir");
	String failureDirectory = rootDirectory + "\\ScreenShots\\Failures\\";
	String successDirctory = rootDirectory + "\\ScreenShots\\Success\\";
	
	
	@BeforeTest()	
	public void beforeTest()
	{
		try
		{
			//------Settings the Capabilities------------
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", "766aeec800c1fcb98d964cb4"); //obtained from the command "adb devices" //-Nex - 00c1fcb98d964cb4
			capabilities.setCapability(CapabilityType.VERSION, "7.1.1");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("appPackage", "com.zippslip");
			capabilities.setCapability("appActivity", "com.zippslip.activity.SplashActivity");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}
		catch(Exception ex)
		{
			System.out.println("Exception occurred: "+ex);
		}
	}
	
	
	@Test()
	public void loginToApplication()
	{
		
		//------Handling the Location pop up for Nexus only ------------
		
		/*
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button")));
		WebElement allowLocation = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		if(allowLocation.isDisplayed() == true)
		{
			allowLocation.click();
		}
		*/
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.zippslip:id/et_username_login")));
		WebElement username = driver.findElement(By.id("com.zippslip:id/et_username_login1"));
		WebElement password = driver.findElement(By.id("com.zippslip:id/et_password_login"));
		MobileElement login = (MobileElement) driver.findElement(By.id("com.zippslip:id/btn_login"));
		
		if(username.isDisplayed() == true && password.isDisplayed()==true)
		{
			System.out.println("Username and Password is found");
			username.sendKeys("edenabc@yopmail.com");
			driver.hideKeyboard();
			password.sendKeys("zippy1");
			
			//-----To hide the keyboard--------
			driver.hideKeyboard();
			
			//-----To tap on the button----------
			TouchAction tc = new TouchAction((MobileDriver) driver);
			tc.tap(login).perform();
			
			//-----Wait for element to be located--------
			
			WebDriverWait waitForLocated = new WebDriverWait(driver, 40); 
			waitForLocated.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.zippslip:id/iv_menu")));
			
			WebElement menu = driver.findElement(By.id("com.zippslip:id/iv_menu"));
			boolean isMenuDisplayed = menu.isDisplayed();
			System.out.println("Menu is displayed: "+isMenuDisplayed);
			Assert.assertEquals(isMenuDisplayed, true);
		}
	}
	
	@AfterTest()
	public void afterTest()
	{
		driver.quit();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		if (result.getStatus() == ITestResult.FAILURE) 
		{
			captureScreenShot(result, "fail");
		}
		if (result.getStatus() == ITestResult.SUCCESS)
		{
			captureScreenShot(result, "pass");
		}
	}
	
	public void captureScreenShot(ITestResult result, String status) 
	{
		try {
			String destDir = "";
			String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "."
					+ result.getMethod().getMethodName();
			
			// To capture screenshot.
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			
			// If status = fail then set folder name "screenshots/Failures"
			if (status.equalsIgnoreCase("fail")) 
			{
				// destDir = "ScreenShots/Failures";
				destDir = failureDirectory;
			}
			// If status = pass then set folder name "screenshots/Success"
			else if (status.equalsIgnoreCase("pass")) 
			{
				destDir = successDirctory;
			}

			// To create folder to store screenshots
			new File(destDir).mkdirs();
			// Set file name with combination of test class name + date time.
			String destFile = passfailMethod + " - " + dateFormat.format(new Date()) 
				+ ".png";

			// Store file at destination folder location
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//MobileElement username = (MobileElement) driver.findElement(By.xpath("//android.widget.EditText[@resource-id='et_username_login']"));
	//username.sendKeys("naman");
	
	//-----------Element Locator------------------
	//android.widget.TextView[@text='Forgot Password?']
	////android.widget.EditText[@resource-id='et_username_login']"
	//android.widget.EditText[contains(@resource-id,'et_username_login')]
	
	
	
		
	//------------Running Code MI---------------------	
		
	/*DesiredCapabilities	capabilities = DesiredCapabilities.android();
	capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
	capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
	capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "My Phone");
	capabilities.setCapability(MobileCapabilityType.VERSION, "5.0.2");
	URL url = new URL("http://127.0.0.1:4723/wd/hub");
	WebDriver driver = new AndroidDriver(url, capabilities);
	driver.get("http://www.quovantis.com");
	String title = driver.getTitle();
	System.out.println(title);
	driver.quit();
	*/
	

	
	
}
