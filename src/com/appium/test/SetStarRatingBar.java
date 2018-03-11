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

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class SetStarRatingBar {
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
	  scrollTo("Rating bar");
	  driver.findElement(By.xpath("//android.widget.TextView[@text='Rating Bar']")).click();
	  
	//Locate threeStarRatingbar.
	  WebElement threeStarRatingbar = driver.findElement(By.id("io.appium.android.apis:id/ratingbar1"));
	  //Get start point of threeStarRatingbar.
	  int startX = threeStarRatingbar.getLocation().getX();
	  System.out.println(startX);
	  //Get end point of threeStarRatingbar.
	  int endX = threeStarRatingbar.getSize().getWidth();
	  System.out.println(endX);
	  //Get vertical location of threeStarRatingbar.
	  int yAxis = threeStarRatingbar.getLocation().getY();
	  
	  //Set threeStarRatingbar tap position to set Rating = 1 star.
	  //You can use endX * 0.3 for 1 star, endX * 0.6 for 2 star, endX * 1 for 3 star.
	  int tapAt = (int) (endX * 0.3);    
	  //Set threeStarRatingbar to Rating = 1.0 using TouchAction class.
	  TouchAction act = new TouchAction(driver);  
	  act.press(tapAt, yAxis).release().perform();

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
