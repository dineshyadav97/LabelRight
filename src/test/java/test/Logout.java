package test;

import java.net.SocketException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Logout {
	public static void main(String args[]) throws InterruptedException,SocketException
	{
		System.setProperty("webdriver.chrome.driver","C:/Users/dinesh.yadav/Downloads/chromedriver_win32/chromedriver.exe" );
		WebDriver driver= new ChromeDriver();
		driver.get("http://52.152.189.35:8080");
		driver.manage().window().maximize();
		System.out.print(driver.getTitle());
		driver.get(driver.getCurrentUrl());
		Thread.sleep(2000);
		driver.findElement(By.id("input28")).sendKeys("lakshmiprasanna.k@tigeranalytics.com");
		driver.findElement(By.id("input36")).sendKeys("Prasanna@18");
		driver.findElement(By.className("button-primary")).click();
		String loginSuccessUrl = "http://52.152.189.35:8080/get-started";
		Thread.sleep(5000);
		
		String logoutSuccessUrl="https://dev-50823058.okta.com/oauth2/aus911jzb95Tj5tX35d7/v1/authorize?client_id=0oa6w8nsx9uSenFgu5d7&nonce=q2A6p0GH5Op8LL7XEuxpkJQYfnI5NBZIcYznIqOS6qHzsFQWp3A8XlzEVWWRFRZJ&redirect_uri=http%3A%2F%2F52.152.189.35%3A8080%2Fhome&response_type=id_token%20token&state=XaQAdZFEG9na0cC291ODKVoFu2QuZUkkHejV6Bu13yYPlyLG350pjwGWNvwg4rvQ&scope=openid%20email%20profile%20offline_access";
		//driver.findElement(By.className("dashboard-logout")).click();
		WebElement l = driver.findElement(By.className("dashboard-logout"));
		JavascriptExecutor je =(JavascriptExecutor)driver;
		je.executeScript("window.scrollTo(0,"+l.getLocation().x+")");
		l.click();
		if(driver.getCurrentUrl().equals(logoutSuccessUrl))
			System.out.println("Logout Successful for username");
		else
			System.out.println("Logout Unsuccessful for username");
		
		Thread.sleep(10000);
		driver.close();
		
		
	}

}
