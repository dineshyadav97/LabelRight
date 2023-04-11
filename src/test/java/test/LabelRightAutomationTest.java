package test;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class LabelRightAutomationTest {
	
	private static WebDriver driver;
	private static Logger log = LogManager.getLogger();
	
	private static Authentication authentication;
	private static ArtworkManualFlows artworkManualFlows;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		//Starting the Application
		System.setProperty("webdriver.chrome.driver",Constants.WebDriverUrl );
		driver= new ChromeDriver();
		driver.get(Constants.loggedInUrl);
		driver.manage().window().maximize();
		log.info("Application Started.");
		authentication = new Authentication(driver);
		artworkManualFlows = new ArtworkManualFlows(driver);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//Closing the Application
		log.info("Application Closed");
		driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		
		//Login
		log.info("Login Process Started.");
		Authentication.Login();
		
	}

	@After
	public void tearDown() throws Exception {
		
		//Logout
		log.info("Logout Process Started.");
		Authentication.Logout();
	}

	@Test
	public void test() throws InterruptedException {
		log.info("Flow1 Started.");
		artworkManualFlows.Flow1();
		
	}

}
