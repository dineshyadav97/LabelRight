package test;

import java.net.SocketException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login {
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
		Assert.assertEquals(driver.getCurrentUrl(),loginSuccessUrl );
		
		Thread.sleep(10000);
		driver.close();
		
		
	}

}
