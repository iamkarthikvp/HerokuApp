package com.HerokuApp.PageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class ApplicationFunctions {

	public static WebDriver driver;

	public Properties prop;
	 
	public Logger log;
	@BeforeSuite( enabled=true,alwaysRun = true)
	public void BSuite() throws Exception {

		System.out.println("Suite is Started");
		
	

		 //FileHandler fileHandler = new FileHandler(System.getProperty("user.dir") +"/logs/MyLogFile.log", true);
		
		//log.addHandler(fileHandler);
		
		//SimpleFormatter formatter = new SimpleFormatter(); 
		//fileHandler.setFormatter(formatter); 
		log = Logger.getLogger(ApplicationFunctions.class.getName());;
		System.setProperty("java.util.logging.SimpleFormatter.format",
	             "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS.%1$tL [%4$s] %2$s - %5$s%6$s%n");
		
		SimpleDateFormat format = new SimpleDateFormat("MMM-y-dd");
		
		FileHandler fh = new FileHandler(System.getProperty("user.dir") +"/logs/"
	                + format.format(Calendar.getInstance().getTime()) + "_logs.log",true);
		
		fh.setFormatter(new SimpleFormatter());
        log.addHandler(fh);
       
        log.setUseParentHandlers(true);

	}

	@BeforeClass( enabled=true,alwaysRun = true)
	public void Setup() {


			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 log.info("implicity wait set for 30 seconds");
			
	
	}

	@AfterClass( enabled=true,alwaysRun = true)
	public void Teardown() {
		driver.quit();

	}

	@AfterSuite( enabled=true,alwaysRun = true)
	public void ASuite() {
		driver = null;

	}
	
	public WebDriver IntializeDriver(String Browser)
	
	
	{
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/Configuration/Config.properties");
			
			Properties prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Config file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Config file not found");
			e.printStackTrace();
		}
		
		//String Browser = prop.getProperty("browser");

		if (Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			log.severe("chrome is started");
		} 
		else if (Browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("firefox is started");
		}
		return driver;
	}

}
