package com.HerokuApp;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.HerokuApp.PageObjects.ApplicationFunctions;
import com.HerokuApp.PageObjects.HomePage;


public class HomePageTest extends ApplicationFunctions{
	
	HomePage Home = new HomePage();


	
	@Parameters({"BaseURL","browser"})
	@BeforeTest ( enabled=true,alwaysRun = true)
	public void cofigset(String URL,String browser)
	{
		
		
		
			driver=IntializeDriver(browser);
			
		
		
		
		System.out.println("Before Test");
		 log.warning("All tests are started");
		driver.manage().window().maximize();
		 log.info("window maximized");
		driver.get(URL);
		log.info("navigated to home");
		
	}

	@Test(priority=1,groups= {"smoke","e2e","regression"})
	public void AomeLinksCounttest()
	{
		
	WebElement links= driver.findElement(Home.content);
	
	List<WebElement>linkslist= links.findElements(By.tagName("a"));
	log.info("finded the links count");

		/*for(int i=0;i<linkslist.size();i++)
		{
			System.out.println(linkslist.get(i).getText());
		}*/
		
	Assert.assertEquals(linkslist.size(), 43);
	log.info("link count is "+linkslist.size());
	log.info("link count test pass");

	}
	
	@Test(priority=2,groups= {"smoke"})
	public void HomeLinkpresenttest()
	{

	
	WebElement links= driver.findElement(Home.content);
	
	List<WebElement>linkslist= links.findElements(By.tagName("a"));

	String link = null;
		for(int i=0;i<linkslist.size();i++)
		{
			 link=linkslist.get(i).getText();
			
			if(link.equalsIgnoreCase("Entry Ad"))
			{
				Assert.assertEquals(link, "Entry Ad");
				log.info("this Link is available in page " +link);
				log.info("Test 2 Pass");
				break; 
			}
			
		}
	}
	
	@Test(priority=3,dataProvider="getdata",groups= {"regression"} )
	
	public void HomeLinkpresentTestwithdataprovider(String linkdata)
	{

	
	WebElement links= driver.findElement(Home.content);
	
	List<WebElement>linkslist= links.findElements(By.tagName("a"));

	String link = null;
		for(int i=0;i<linkslist.size();i++)
		{
			 link=linkslist.get(i).getText();
			
			if(link.equalsIgnoreCase(linkdata))
			{
				Assert.assertEquals(link, linkdata);
				log.info("this Link is available in page " +link);
				log.info("Test 3 Pass");
				break; 
			}
			
		}
	}
	@AfterTest ( enabled=true,alwaysRun = true)
	public void cofigout()
	{
		log.config("After Test");
		
		log.warning("All tests are ended");
		driver.quit();
		
	}
	
	@DataProvider
	
	public Object[][] getdata()
	{
		Object[][] data= new Object[5][1];
		
		data[0][0]="Dynamic Loading";
		data[1][0]="Drag and Drop";
		data[2][0]="Geolocation";
		data[3][0]="Infinite Scroll";
		data[4][0]="Key Presses";
		
		return data;
		
	}
}


