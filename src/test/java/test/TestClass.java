package test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass {

	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver","C:/Users/dinesh.yadav/Downloads/chromedriver_win32/chromedriver.exe" );
		WebDriver webDriver= new ChromeDriver();
		webDriver.get("http://52.152.189.35:8080/get-started");
		System.out.print(webDriver.getTitle());
		webDriver.close();
		
	}
}
