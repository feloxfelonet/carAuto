package com.registration.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

	public static WebDriver driver;

	public static Properties appConfigProp;
	public static String inputfilepath, outputfilepath;
	
	public TestBase() {
		try {
			File appconfigfile = new File(
					System.getProperty("user.dir") + "/src/main/java/com/registration/config/appconfig.properties");
			final FileInputStream appConfigPropFile = new FileInputStream(appconfigfile);
			appConfigProp = new Properties();
			appConfigProp.load(appConfigPropFile);
			appConfigPropFile.close();
			String inputfilename = appConfigProp.getProperty("inputfile");
			String outputfilename = appConfigProp.getProperty("outputfile");
			inputfilepath = TestBase.class.getClassLoader().getResource(inputfilename).getPath();
			outputfilepath = TestBase.class.getClassLoader().getResource(outputfilename).getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void initializeDriver() {		
		//WebDriverManager.chromedriver().setup();
		// Set Chrome driver path
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
		// Initialize Chrome driver
		driver = new ChromeDriver();
		driver.manage().window().maximize();	
		driver.get(appConfigProp.getProperty("url"));
		//driver.get("https://www.cazoo.co.uk/value-my-car/");
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void navigateToWebsite(String url) {
		driver.get(url);
	}
}