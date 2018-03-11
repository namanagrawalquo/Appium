package com.appium.test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class SelectRadioButton {

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
	 public void select() throws InterruptedException {
	 // Click on Views
	  driver.findElement(By.xpath("//android.widget.TextView[@index='10']")).click();
	  // Click on Controls.
	  driver.findElement(By.xpath("//android.widget.TextView[@index='4']")).click();
	  // Click on 2. Dark Theme.
	  driver.findElement(By.xpath("//android.widget.TextView[@index='1']")).click();
	  // Click on Radio button
	  driver.findElement(By.id("io.appium.android.apis:id/radio1")).click();
	 }

	 @AfterTest
	 public void End() {
	  driver.quit();
	 }
}
