package test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
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
	private static ArtworkInterrupt artworkInterrupt;
	
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
		artworkInterrupt = new ArtworkInterrupt(driver);
		
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
	public void test1() throws InterruptedException, IOException {
		
		FileInputStream artworkRequestsFile = new FileInputStream(Constants.ArtworkRequests);
		
		XSSFWorkbook artworkRequestWB = new XSSFWorkbook(artworkRequestsFile);
		XSSFSheet sheet = artworkRequestWB.getSheet("Sheet1");
		Iterator<Row> itr = sheet.iterator();   
		itr.next(); // not taking the header
		while (itr.hasNext())                 
		{  
		Row row = itr.next();  
		Iterator<Cell> cellIterator = row.cellIterator();    
		
		Cell artworkUrlCell = row.getCell(2);
		String artworkUrl = artworkUrlCell.getStringCellValue(); 
		Cell lidUrlCell = row.getCell(3);
		String lidUrl = lidUrlCell.getStringCellValue(); 
		Cell regionCategoryCell = row.getCell(0);
		String regionCategory = regionCategoryCell.getStringCellValue(); 
		Cell packageTypeCell = row.getCell(1);
		String packageType = packageTypeCell.getStringCellValue();
		
		RegionCategoryPackageType regionCategoryPackageType  = artworkManualFlows.convertToOptions(regionCategory,packageType);
		
		log.info("Adhoc Flow Started.");
		ArtworkDto artworkDto = artworkManualFlows.Flow1(regionCategoryPackageType.getRegionCategory(),regionCategoryPackageType.getPackageType(),artworkUrl,lidUrl);
		log.info(artworkDto.getArtworkID()+" "+artworkDto.getErrorCount());
		
        WebElement start_page = driver.findElement(By.xpath("/html/body/app-root/header/app-header/div/h3/span"));
		js.executeScript("arguments[0].click();", start_page);

		log.info("Checking if error count in results page is equal to error count in reviewed page.");
		artworkManualFlows.checkErrorCount(artworkDto,regionCategoryPackageType.getRegionCategory());
		
		start_page = driver.findElement(By.xpath("/html/body/app-root/main/app-homepage/div/header/app-header/div/h3/span"));
		js.executeScript("arguments[0].click();", start_page);

		
		}
		
	
		WebElement start_page = driver.findElement(By.xpath("/html/body/app-root/header/app-header/div/h3/span"));
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
	
		log.info("Artwork interrupt flow started");
		artworkInterrupt.artworkInterruptFlow(Constants.RegionCategory_US_SocialBeverages,Constants.Package_Primary,Constants.Flow1ArtworkUrl);

	}
	
	
	

}
