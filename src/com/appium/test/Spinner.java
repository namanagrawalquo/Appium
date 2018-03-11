package com.appium.test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Spinner {

	AndroidDriver driver;

	 @BeforeTest
	 public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("deviceName", "ZY2234ZDFF"); //obtained from the command "adb devices" //-Nex - 00c1fcb98d964cb4
	capabilities.setCapability(CapabilityType.VERSION, "7.1.1");
	capabilities.setCapability("platformName", "Android");
	  capabilities.setCapability("appPackage", "io.appium.android.apis");
	  capabilities.setCapability("appActivity","io.appium.android.apis.ApiDemos");
	  driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 }

	 @Test
	 public void spinner() throws InterruptedException {
	 // Click on Views
	  driver.findElement(By.xpath("//android.widget.TextView[@index='10']")).click();
	  // Scroll to Spinner
	  scrollTo("Spinner");
	  // Click on Spinner.
	  driver.findElement(By.xpath("//android.widget.TextView[@text='Spinner']")).click();
	  WebElement ele = driver.findElementById("io.appium.android.apis:id/spinner2");
	  //Click on Planet drop down to open drop down list.
	  ele.findElement(By.id("android:id/text1")).click();
	  // Scroll till element which contains "Pluto" text In drop down list.
	  scrollTo("Pluto");
	  //Select "Pluto" from drop down list Items.
	  driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Pluto']")).click();
	 }

	 @AfterTest
	 public void End() {
	  driver.quit();
	 }
	 
	 public void scrollTo(String text)
	 {                
		 driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+text+"\").instance(0))");
	 }
}
