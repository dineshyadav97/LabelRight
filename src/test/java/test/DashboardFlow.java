package test;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardFlow {

	private static WebDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;
	private static Logger log = LogManager.getLogger();

	public DashboardFlow(WebDriver driver) {
		DashboardFlow.driver = driver;
		DashboardFlow.js = (JavascriptExecutor) driver;
		DashboardFlow.wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
	}

	public void ReviewInProgress() throws InterruptedException {
		
		String search = "lakshmi";

		WebElement dashboardBtn = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/main/app-get-started/div/div/div/div/div[4]/button[1]")));
		
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", dashboardBtn);
				
		WebElement reviewInProgress = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/app-root/main/app-homepage/div/div/div[1]/button[1]")));
		js.executeScript("arguments[0].click();", reviewInProgress);
		
		WebElement searchTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[1]/div[3]/input")));
		js.executeScript("arguments[0].value = '" +search + "';", searchTab);
		
		Thread.sleep(3000);
		
//		WebElement rowData = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr[1]/td[6]/span")));
//		String data = rowData.getText();
//		Assert.assertTrue("fetched row doesn't contain the searched word", data.contains(search));
		
		List<WebElement> page1Data = driver.findElements(By.xpath("/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr"));
		int i=0;
		for ( ; i < page1Data.size(); i++) {
			String data = page1Data.get(i).getText();
			String data2 = data.split(" ")[1];
			if(data2.contains(search)) {
				continue;
			}
			else {
				break;
			}
		}
		
		if(i==page1Data.size()) {
		log.info("verification successful");
		}
		else {
			log.info("verification unsuccessful");
		}
		

	}
	
	public void Reviewed() throws InterruptedException {
        
		WebElement dashboard_project_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//app-root//app-get-started[@class='ng-star-inserted']/div/div/div[@class='col-8 mx-auto']//button[@class='btn btn-primary dashboard-btn']")));
		js.executeScript("arguments[0].click();", dashboard_project_btn);

		WebElement reviewed = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//app-root//app-homepage[@class='ng-star-inserted']/div[@class='dashboardpageContainer']//div[@class='tabsDiv']/button[2]/span[@class='tabheader']")));
		js.executeScript("arguments[0].click();", reviewed);

		WebElement search_bar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//app-root//app-homepage[@class='ng-star-inserted']/div[@class='dashboardpageContainer']/div[@class='maindiv']//app-dashboardtable[@class='ng-star-inserted']/div[@class='dashboardtablemain']//input[@type='text']")));
		search_bar.sendKeys("neha");
		js.executeScript("arguments[0].click();", search_bar);

		List<WebElement> tabs = driver.findElements(By.xpath(
				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr"));

		int i = 0;

		for (; i < tabs.size(); i++) {
			String data = tabs.get(i).getText();
			String data2 = data.split(" ")[1];
			if (data2.contains("neha")) {
				System.out.println(data2);
				continue;
			} else {
				break;
			}
		}

		if (i == tabs.size()){
			log.info("verification succesfull");
		} else {
			log.info("verification unsuccesfull");

		}
	}

}
