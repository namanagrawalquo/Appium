package com.appium.test;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DragDrop {
 
	 //Object reference of AndroidDriver.
	 AndroidDriver driver;
	
	 @BeforeTest
	 public void setUp() throws MalformedURLException {
	  
		  // Created object of DesiredCapabilities class.
		  DesiredCapabilities capabilities = new DesiredCapabilities();
		  
		  capabilities.setCapability("deviceName", "766aeec8");
		  capabilities.setCapability("browserName", "Android");
		  capabilities.setCapability("platformVersion", "5.0.2");
		  capabilities.setCapability("platformName", "Android");
		  capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
		  capabilities.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");
		  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 }
	
	 @Test
	 public void dragDrop() 
	 {
	  
		  driver.findElementById("com.mobeta.android.demodslv:id/activity_desc").click();
		
		  WebElement ele1 = (WebElement) driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle").get(2);
		  WebElement ele2 = (WebElement) driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle").get(5);
		
		  
		  TouchAction action = new TouchAction((MobileDriver) driver);
		  //It will hold tap on 3rd element and move to 6th position and then release tap.
		  action.longPress(ele1).moveTo(ele2).release().perform();  
	 }
	
	 @AfterTest
	 public void End() 
	 {
		  driver.quit();
	 }
}
