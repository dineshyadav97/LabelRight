package test;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Authentication {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static JavascriptExecutor js;
	private static Logger log = LogManager.getLogger();
	
	
	public Authentication(WebDriver driver) {
		Authentication.driver=driver;
		Authentication.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Authentication.js = (JavascriptExecutor)  driver;
	}
	
	public static void Login() throws InterruptedException
	{
		WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"input28\"]")));
        username.sendKeys(Constants.username);
        
        WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"input36\"]")));
        password.sendKeys(Constants.password);
        driver.findElement(By.className("button-primary")).click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().equals(Constants.loggedInUrl))
			log.info("Login Successful for username: "+Constants.username);
		else
			log.warn("Login Unsuccessful for username: "+Constants.username);
	}
	
	public static void Logout() throws InterruptedException
	{
	
		
		WebElement logout=driver.findElement(By.xpath("/html/body/app-root/header/app-header/div/div/button"));
		js.executeScript("arguments[0].click();", logout);
		
		Thread.sleep(2000);
		if(driver.getCurrentUrl().equals(Constants.loggedInUrl))
			log.info("Logout Unsuccessful for username: "+Constants.username);
		else
			log.warn("Logout Successful for username: "+Constants.username);
		
		Thread.sleep(3000);
	}

	
	
}
