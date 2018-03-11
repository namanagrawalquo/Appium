package com.appium.test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class PerformLongPress {

	AndroidDriver driver;

	 @BeforeTest
	 public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("deviceName", "ZY2234ZDFF"); //obtained from the command "adb devices" //-Nex - 00c1fcb98d964cb4
	capabilities.setCapability(CapabilityType.VERSION, "7.1.1");
	capabilities.setCapability("platformName", "Android");
	  capabilities.setCapability("appPackage", "com.android.dialer");
	  capabilities.setCapability("appActivity","com.android.dialer.DialtactsActivity");
	  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 }

	 @Test
	 public void LongPress() throws InterruptedException {
		 //Click on dial pad button to open dialer pad.
		  driver.findElementById("com.android.dialer:id/floating_action_button").click();
		  //Create object of TouchAction class.
		  TouchAction Action = new TouchAction(driver);
		  //Create action chain using TouchAction class reference to perform long press action on button 0 of dialer pad.
		  Action.longPress(driver.findElement(By.id("com.android.dialer:id/zero"))).perform();
		  //Get the result from dial text box.
		  String result = driver.findElementById("com.android.dialer:id/digits").getText();
		  //Compare actual and expected result using testng assertion.
		  Assert.assertEquals(result, "+", "Actual value is : "+ result+ " did not match with expected value: +");  
	 }

	 @AfterTest
	 public void End() {
	  driver.quit();
	 }
}
