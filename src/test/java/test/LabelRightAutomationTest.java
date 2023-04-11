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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class LabelRightAutomationTest {
	
	private static WebDriver driver;
	private static final String username="lakshmiprasanna.k@tigeranalytics.com"; 
	private static final String password="Prasanna@18";
	private static Logger log = LogManager.getLogger();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe" );
		driver= new ChromeDriver();
		driver.get("http://52.152.189.35:8080/get-started");
		driver.manage().window().maximize();
		log.info("Application Started.");
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//driver.quit();
	}

	@Before
	public void setUp() throws Exception {
		
	
		driver.get(driver.getCurrentUrl());
		Thread.sleep(2000);
		driver.findElement(By.id("input28")).sendKeys(username);
		driver.findElement(By.id("input36")).sendKeys(password);
		driver.findElement(By.className("button-primary")).click();
		String loginSuccessUrl = "http://52.152.189.35:8080/get-started";
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equals(loginSuccessUrl))
			log.info("Login Successful for username: "+username);
		else
			log.warn("Login Unsuccessful for username: "+username);
		
		Thread.sleep(5000);
		
		
		
	}

	@After
	public void tearDown() throws Exception {
		//logout
		String logoutSuccessUrl="https://dev-50823058.okta.com/oauth2/aus911jzb95Tj5tX35d7/v1/authorize?client_id=0oa6w8nsx9uSenFgu5d7&nonce=q2A6p0GH5Op8LL7XEuxpkJQYfnI5NBZIcYznIqOS6qHzsFQWp3A8XlzEVWWRFRZJ&redirect_uri=http%3A%2F%2F52.152.189.35%3A8080%2Fhome&response_type=id_token%20token&state=XaQAdZFEG9na0cC291ODKVoFu2QuZUkkHejV6Bu13yYPlyLG350pjwGWNvwg4rvQ&scope=openid%20email%20profile%20offline_access";
		driver.findElement(By.className("dashboard-logout")).click();
		if(driver.getCurrentUrl().equals(logoutSuccessUrl))
			log.info("Logout Successful for username: "+username);
		else
			log.warn("Logout Unsuccessful for username: "+username);
		
		Thread.sleep(5000);
	}

	@Test
	public void test() throws InterruptedException {
		log.info("test");
		
//		WebElement location = driver.findElement(By.xpath("//div[@class='mat-form-field-infix ng-tns-c54-0']"));
//		location.click();
//		
		WebElement chosenRegion = driver.findElement(By.xpath("//mat-option[@id ='mat-option-3']"));
		chosenRegion.click();
		
		Thread.sleep(3000);
		
		WebElement newAdHocReview = driver.findElement(By.cssSelector(password));
		
	}

}
