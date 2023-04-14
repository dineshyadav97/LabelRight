package test;

import static org.junit.Assert.*;

import java.time.Duration;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class LabelRightAutomationTest {
	
	private static WebDriver driver;
	private static Logger log = LogManager.getLogger();
	private static JavascriptExecutor js;
	
	
	private static Authentication authentication;
	private static ArtworkManualFlows artworkManualFlows;
	private static DashboardFlow dashboardFlow;
	private static ResumeFunctionality resumeFunctionality;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		//Starting the Application
		System.setProperty("webdriver.chrome.driver",Constants.WebDriverUrl );
		driver= new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.get(Constants.loggedInUrl);
		driver.manage().window().maximize();
		log.info("Application Started.");
		authentication = new Authentication(driver);
		artworkManualFlows = new ArtworkManualFlows(driver);
		dashboardFlow = new DashboardFlow(driver);
		resumeFunctionality = new ResumeFunctionality(driver);
		
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
	public void test1() throws InterruptedException {
		
		log.info("Adhoc Flow1 Started.");
		artworkManualFlows.Flow1(Constants.RegionCategory_US_SocialBeverages,Constants.Package_Primary,Constants.Flow1ArtworkUrl,Constants.Flow1LidUrl);
		
		
        WebElement start_page = driver.findElement(By.xpath("/html/body/app-root/header/app-header/div/h3/span"));
		js.executeScript("arguments[0].click();", start_page);

		
		log.info("Adhoc Flow2 Started.");
		artworkManualFlows.Flow1(Constants.RegionCategory_US_SocialBeverages,Constants.Package_Primary,Constants.Flow1ArtworkUrl,Constants.Flow1LidUrl);
		
		start_page = driver.findElement(By.xpath("/html/body/app-root/header/app-header/div/h3/span"));
		js.executeScript("arguments[0].click();", start_page);

		log.info("Dashboard Flow - Review In Progress Started.");
		dashboardFlow.ReviewInProgress();
		
		start_page = driver.findElement(By.xpath("/html/body/app-root/main/app-homepage/div/header/app-header/div/h3/span"));
		js.executeScript("arguments[0].click();", start_page);
		
		log.info("Dashboard Flow - Reviewed Started.");
		dashboardFlow.Reviewed();
		
		start_page = driver.findElement(By.xpath("/html/body/app-root/main/app-homepage/div/header/app-header/div/h3/span"));
		js.executeScript("arguments[0].click();", start_page);
		
		log.info("Dashboard Flow - Review In Progress - Resume Started.");
		resumeFunctionality.resumeFlow();
	
	}
	
	
	

}
