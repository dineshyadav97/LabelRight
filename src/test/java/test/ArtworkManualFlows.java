package test;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.netty.util.Constant;

public class ArtworkManualFlows {

	
	private static WebDriver driver;
	private static JavascriptExecutor js;
	private static WebDriverWait wait;
	private static Logger log = LogManager.getLogger();

	
	public ArtworkManualFlows(WebDriver driver) {
		ArtworkManualFlows.driver=driver;
		ArtworkManualFlows.js=(JavascriptExecutor)  driver;
		ArtworkManualFlows.wait = new WebDriverWait(driver,Duration.ofSeconds(10000));
	}
	
	public void Flow1(int regionOption,int packageType,String artworkUrl,String lidUrl) throws InterruptedException
	{
	
		WebElement region_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/header/app-header/div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-icon")));
        js.executeScript("arguments[0].click();", region_selection_element);

        WebElement region_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option["+regionOption+"]/span")));
        js.executeScript("arguments[0].click();", region_option_element);
        
        WebElement adhoc_project_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-get-started/div/div/div/div/div[4]/button[2]")));
        js.executeScript("arguments[0].click();", adhoc_project_btn);
        
        Thread.sleep(2000);

        WebElement package_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[1]/div[2]/mat-form-field/div/div[1]/div/mat-select/div/div[1]/span/span")));
        js.executeScript("arguments[0].click();", package_selection_element);
        
        WebElement package_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option["+packageType+"]/span")));
        js.executeScript("arguments[0].click();", package_option_element);

        WebElement aw_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[1]/app-file-upload/div/div[2]/div/input")));
        File artwork = new File(Constants.Flow1ArtworkUrl);
        log.info(artwork);
        aw_upload_element.sendKeys(artwork.getAbsolutePath());
        log.info("Artwork Uploaded");
        
        Thread.sleep(2000);
        
        WebElement lid_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/main/app-upload-page/div/div/div/div[2]/div/div[2]/app-file-upload/div/div[2]/div/input")));
        File lid = new File(Constants.Flow1LidUrl);
        log.info(lid);
        lid_upload_element.sendKeys(lid.getAbsolutePath());
        log.info("Lid Uploaded");
        
        
        try {
            WebElement upload_page_next = new WebDriverWait(driver,Duration.ofSeconds(70) ).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
            js.executeScript("arguments[0].click();", upload_page_next);
            
        } catch (Exception e) {
            log.error("Error in UploadPage");
            log.error(e);
        }

        try {
            WebElement verify_page_next = new WebDriverWait(driver,Duration.ofSeconds(70) ).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-verify/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
            js.executeScript("arguments[0].click();", verify_page_next);
        } catch (Exception e) {
            log.error("Error in VerifyPage");
            log.error(e);
        }

        try {
            WebElement result_page_save = new WebDriverWait(driver,Duration.ofSeconds(70) ).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-results/div/div/div[1]/app-side-button-panel/div/div/div[2]/div[1]/button")));
            js.executeScript("arguments[0].click();", result_page_save);
        } catch (Exception e) {
            log.error("Error in ResultsPage");
            log.error(e);
        }

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        String ArtworkID = (String)js.executeScript("return window.sessionStorage.getItem('projectId');");
        log.info("Artwork Request Successful: \nArtwork Request ID: "+ArtworkID);

		Thread.sleep(3000);
		
	}
	
}
