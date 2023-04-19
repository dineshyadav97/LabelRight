package test;

import java.io.File;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultInterruptionFlow {

	private static WebDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;
	private static Logger log = LogManager.getLogger();

	public ResultInterruptionFlow (WebDriver driver) {
		ResultInterruptionFlow .driver = driver;
		ResultInterruptionFlow .js = (JavascriptExecutor) driver;
		ResultInterruptionFlow .wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
	}

	public void Flow6(int regionOption, int packageType, String artworkUrl, String lidUrl) throws InterruptedException {

		ArtworkDto artworkDto = new ArtworkDto();

		WebElement region_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/header/app-header/div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-icon")));
		js.executeScript("arguments[0].click();", region_selection_element);
		log.info("region_selection_element");
		Thread.sleep(4000);

		WebElement region_option_element = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[1]/span")));
		js.executeScript("arguments[0].click();", region_option_element);
		log.info("region_option_element");
		Thread.sleep(4000);

		WebElement New_Ad_hoc_review_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//app-root//app-get-started[@class='ng-star-inserted']/div/div/div[@class='col-8 mx-auto']//div[@class='menulist']/button[2]")));
		js.executeScript("arguments[0].click();", New_Ad_hoc_review_btn);
		log.info("New_Ad_hoc_review_btn");
		Thread.sleep(4000);

		WebElement package_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[1]/div[2]/mat-form-field/div/div[1]/div/mat-select/div/div[1]/span/span")));
		js.executeScript("arguments[0].click();", package_selection_element);
		log.info("package_selection_element");
		Thread.sleep(4000);

		WebElement package_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + packageType + "]/span")));
		js.executeScript("arguments[0].click();", package_option_element);
		log.info("package_option_element");

		Thread.sleep(4000);

		WebElement aw_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[1]/app-file-upload/div/div[2]/div/input")));
		File artwork = new File(artworkUrl);
		log.info(artwork);
		aw_upload_element.sendKeys(artwork.getAbsolutePath());
		log.info("Artwork Uploaded");
		Thread.sleep(7000);

		WebElement lid_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[2]/app-file-upload/div/div[2]/div/input")));
		File lid = new File(Constants.Flow1LidUrl);
		log.info(lid);
		lid_upload_element.sendKeys(lid.getAbsolutePath());
		log.info("Lid Uploaded");
		Thread.sleep(4000);


		String ArtworkID = (String) js.executeScript("return window.sessionStorage.getItem('projectId');");
		log.info("Artwork Request Successful: \nArtwork Request ID: " + ArtworkID);

		artworkDto.setArtworkID(Integer.parseInt(ArtworkID));

		WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
		js.executeScript("arguments[0].click();", nextButton);
		log.info("next button is clicked");
		
		log.info("Verify page");
		Thread.sleep(4000);
		
		WebElement nextButton_2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/main/app-verify/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
		js.executeScript("arguments[0].click();", nextButton_2);
		log.info("next button is clicked");
		
		log.info("Result page");
		Thread.sleep(4000);

		WebElement LabelRight_btn = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/app-root/header/app-header/div/h3/span")));
		js.executeScript("arguments[0].click();", LabelRight_btn);
		log.info("LabelRight_btn");
		Thread.sleep(4000);

		WebElement dashboard_project_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//app-root//app-get-started[@class='ng-star-inserted']/div/div/div[@class='col-8 mx-auto']//button[@class='btn btn-primary dashboard-btn']")));
		js.executeScript("arguments[0].click();", dashboard_project_btn);
		log.info("dashboard_project_btn");
		Thread.sleep(4000);

		WebElement ReviewInProgress = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/app-root/main/app-homepage/div/div/div[1]/button[1]/span")));
		js.executeScript("arguments[0].click();", ReviewInProgress);
		log.info("ReviewInProgress");
		Thread.sleep(4000);


		WebElement search_bar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[1]/div[3]/input")));
		search_bar.sendKeys("" + artworkDto.getArtworkID());
		js.executeScript("arguments[0].click();", search_bar);
		Thread.sleep(4000);

		WebElement resumeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr[1]/td[11]/button")));
		js.executeScript("arguments[0].click();", resumeButton);
		log.info("resume button is clicked");
		Thread.sleep(7000);

	}
}