package common;

import java.io.*;
import java.time.Duration;
import java.io.FileReader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class common {

	public static Logger logger;

	//This method will open browser according to browser name provided
	public static WebDriver openBrowser(WebDriver driver,String browsername) {
		logger = Logger.getLogger("common");
		PropertyConfigurator.configure("log4j.properties");

		if(browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else
		if(browsername.equalsIgnoreCase("chrome-headless")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
		}
		else
		if(browsername.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\drivers\\geckodriver.exe");    
			driver= new FirefoxDriver(); 
		}
		else logger.info("selected browser is not present ="+browsername); 
		driver.manage().window().maximize();
		return driver;
	}

	//This method will read json file depends on path
	public static JSONObject readJSON(String path) throws IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(path));

		JSONObject jsonObject =  (JSONObject) obj;
		return jsonObject;
	}
	
	//global method for implicit wait
	public static void waitForTime() throws InterruptedException
	{
		Thread.sleep(5000);	
	}
	
	//This method will close browser instance.
	public static void closeBrowser(WebDriver driver) {

		driver.quit();
		driver=null;
	}
}