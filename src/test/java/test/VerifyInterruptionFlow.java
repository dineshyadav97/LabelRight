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

public class VerifyInterruptionFlow {

	private static WebDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;
	private static Logger log = LogManager.getLogger();

	public VerifyInterruptionFlow(WebDriver driver) {
		VerifyInterruptionFlow.driver = driver;
		VerifyInterruptionFlow.js = (JavascriptExecutor) driver;
		VerifyInterruptionFlow.wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
	}

	public void Flow5(int regionOption, int packageType, String artworkUrl, String lidUrl) throws InterruptedException {

		ArtworkDto artworkDto = new ArtworkDto();

		WebElement region_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/header/app-header/div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-icon")));
		js.executeScript("arguments[0].click();", region_selection_element);

		WebElement region_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + regionOption + "]/span")));
		js.executeScript("arguments[0].click();", region_option_element);

		WebElement New_Ad_hoc_review_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//app-root//app-get-started[@class='ng-star-inserted']/div/div/div[@class='col-8 mx-auto']//div[@class='menulist']/button[2]")));
		js.executeScript("arguments[0].click();", New_Ad_hoc_review_btn);
		log.info("New_Ad_hoc_review_btn");

		Thread.sleep(3000);

		WebElement package_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[1]/div[2]/mat-form-field/div/div[1]/div/mat-select/div/div[1]/span/span")));
		js.executeScript("arguments[0].click();", package_selection_element);
		log.info("package_selection_element");

		WebElement package_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + packageType + "]/span")));
		js.executeScript("arguments[0].click();", package_option_element);
		log.info("package_option_element selected");
		Thread.sleep(3000);

		
		WebElement aw_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[1]/app-file-upload/div/div[2]/div/input")));
		File artwork = new File(artworkUrl);
		log.info(artwork);
		aw_upload_element.sendKeys(artwork.getAbsolutePath());
		log.info("Artwork Uploaded");
		Thread.sleep(2000);

		WebElement lid_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[2]/app-file-upload/div/div[2]/div/input")));
		File lid = new File(Constants.Flow1LidUrl);
		log.info(lid);
		lid_upload_element.sendKeys(lid.getAbsolutePath());
		log.info("Lid Uploaded");

		String ArtworkID = (String) js.executeScript("return window.sessionStorage.getItem('projectId');");
		log.info("Artwork Request Successful: \nArtwork Request ID: " + ArtworkID);

		artworkDto.setArtworkID(Integer.parseInt(ArtworkID));

		try {
			WebElement upload_page_next = new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(
							"/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
			js.executeScript("arguments[0].click();", upload_page_next);
			log.info("Upload Button CLicked.");
		} catch (Exception e) {
			log.error("Error in UploadPage");
			log.error(e);
		}

		String verify_page_next_xpath1 = "/html/body/app-root/main/app-verify/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button";
		WebElement verify_page_next1 = new WebDriverWait(driver, Duration.ofSeconds(100))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(verify_page_next_xpath1)));

		Thread.sleep(2000);
		log.info("Interrupting Verify page");

		WebElement LabelRight_btn = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("/html/body/app-root/header/app-header/div/h3/span")));
		js.executeScript("arguments[0].click();", LabelRight_btn);
		log.info("LabelRight_btn");
		Thread.sleep(2000);

		region_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
				"/html/body/app-root/header/app-header/div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-icon")));
		js.executeScript("arguments[0].click();", region_selection_element);

		region_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[" + regionOption + "]/span")));
		js.executeScript("arguments[0].click();", region_option_element);

		WebElement dashboard_project_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//app-root//app-get-started[@class='ng-star-inserted']/div/div/div[@class='col-8 mx-auto']//button[@class='btn btn-primary dashboard-btn']")));
		js.executeScript("arguments[0].click();", dashboard_project_btn);
		log.info("dashboard_project_btn clicked");

		WebElement ReviewInProgress = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/app-root/main/app-homepage/div/div/div[1]/button[1]/span")));
		js.executeScript("arguments[0].click();", ReviewInProgress);
		log.info("ReviewInProgress");

		WebElement search_bar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[1]/div[3]/input")));
		search_bar.sendKeys("" + artworkDto.getArtworkID());
		js.executeScript("arguments[0].click();", search_bar);
		Thread.sleep(3000);

		WebElement resumeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"/html/body/app-root/main/app-homepage/div/div/div[2]/app-dashboardtable/div/div[2]/table/tbody/tr[1]/td[11]/button")));
		js.executeScript("arguments[0].click();", resumeButton);
		log.info("resume button is clicked");
		Thread.sleep(3000);

		if (driver.getCurrentUrl().equals("http://52.152.189.35:8080/upload")) {

			try {
				WebElement upload_page_next = new WebDriverWait(driver, Duration.ofSeconds(100))
						.until(ExpectedConditions.elementToBeClickable(By.xpath(
								"/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
				js.executeScript("arguments[0].click();", upload_page_next);
				log.info("Upload Button CLicked.");
			} catch (Exception e) {
				log.error("Error in UploadPage");
				log.error(e);
			}

			Thread.sleep(2000);

		}

		try {

			String loading_container_xpath = "/html/body/app-root/app-page-loading/div/app-loading-spinner/div/div";
			Boolean loading_container_element = new WebDriverWait(driver, Duration.ofSeconds(400))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loading_container_xpath)));

			String verify_page_next_xpath = "/html/body/app-root/main/app-verify/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button";
			WebElement verify_page_next = new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(verify_page_next_xpath)));

			js.executeScript("arguments[0].click();", verify_page_next);

			log.info("Verify Button Clicked.");
		} catch (Exception e) {
			log.error("Error in VerifyPage");
			log.error(e);
		}

		try {

			String loading_container_xpath = "/html/body/app-root/app-page-loading/div/app-loading-spinner/div/div";
			Boolean loading_container_element = new WebDriverWait(driver, Duration.ofSeconds(400))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loading_container_xpath)));

			String results_page_next_xpath = "/html/body/app-root/main/app-results/div/div/div[1]/app-side-button-panel/div/div/div[2]/div[1]/button";
			WebElement results_page_next = new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(results_page_next_xpath)));

			WebElement errorCount = driver
					.findElement(By.xpath("/html/body/app-root/main/app-results/div/div/div[1]/span"));
			log.info("Error Count: " + errorCount.getText());

			artworkDto.setErrorCount(Integer.parseInt(errorCount.getText().substring(0, 1)));

			js.executeScript("arguments[0].click();", results_page_next);

			log.info("Save button clicked");
		} catch (Exception e) {
			log.error("Error in ResultsPage");
			log.error(e);
		}

		try {

			String loading_container_xpath = "/html/body/app-root/app-page-loading/div/app-loading-spinner/div/div";
			Boolean loading_container_element = new WebDriverWait(driver, Duration.ofSeconds(400))
					.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loading_container_xpath)));

			String results_page_next_xpath = "/html/body/app-root/main/app-all-done/div/div/div[2]/div/div[3]/div[1]/a";
			WebElement downloadButton = new WebDriverWait(driver, Duration.ofSeconds(100))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(results_page_next_xpath)));

			js.executeScript("arguments[0].click();", downloadButton);

			log.info("download button is clicked");
		} catch (Exception e) {
			log.error("Error in DownloadPage");
			log.error(e);
		}
		Thread.sleep(3000);

////		WebElement nextButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
////				"/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
////		js.executeScript("arguments[0].click();", nextButton1);
////		log.info("next button is clicked, going to verify page");
//
//		WebElement nextButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"/html/body/app-root/main/app-verify/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
//		
//		Thread.sleep(5000);
//		
//		js.executeScript("arguments[0].click();", nextButton2);
//		log.info("second next button is clicked, going to results page");
//
//		WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"/html/body/app-root/main/app-results/div/div/div[1]/app-side-button-panel/div/div/div[2]/div[1]/button")));
//		js.executeScript("arguments[0].click();", saveButton);
//		log.info("save button is clicked, going to dwnld page");
//
//		WebElement dwnldButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-all-done/div/div/div[2]/div/div[3]/div[1]/a")));
////		js.executeScript("arguments[0].click();", dwnldButton);
////		log.info("download button is clicked");
//
//		Thread.sleep(3000);

	}
}
