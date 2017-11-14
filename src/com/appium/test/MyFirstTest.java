package com.appium.test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
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
import org.testng.annotations.AfterTest; //@AfterTest annotated method will be executed when all @Test annotated methods completed its execution
import org.testng.annotations.BeforeTest; //@Before Test annotated method will be executed before any @Test method
import org.testng.annotations.Test; //@Test annotation describes method as a test method or part of your test

public class MyFirstTest {
	
	//Running Code
	//WebDriver driver;
	@Test
	
	public void SplashScreen() throws MalformedURLException, InterruptedException {
		
		
	//------Settings the Capabilities------------
		
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("deviceName", "766aeec800c1fcb98d964cb4"); //obtained from the command "adb devices" //-Nex - 00c1fcb98d964cb4
	capabilities.setCapability(CapabilityType.VERSION, "7.1.1");
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("appPackage", "com.zippslip");
	capabilities.setCapability("appActivity", "com.zippslip.activity.SplashActivity");
	AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	
	//------Handling the Location pop up------------
	
	/*
	WebDriverWait wait = new WebDriverWait(driver, 40);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button")));
	WebElement allowLocation = driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
	if(allowLocation.isDisplayed() == true)
	{
		allowLocation.click();
	}
	*/
	
	//------Finding the Username and Password fields----------
	
	WebDriverWait wait = new WebDriverWait(driver, 40);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.zippslip:id/et_username_login")));
	WebElement username = driver.findElement(By.id("com.zippslip:id/et_username_login"));
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
	
	//driver.quit();
	
	
	
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

	//@Test
	public void Login(){
		
		
		//driver.findElement(By.xpath("//android.widget.ImageButton[contains(@resource-id,'digit3')]")).click();
		//calcButtons.get(4).click();
		
//	driver.findElement(By.xpath("android.widget.ImageView")).click(); //Open drop down country menu
	
	}

	//@AfterTest
	public void end()
	{
	 //Close application
	}

}
