package test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Example {
    public static void main(String[] args) {
        String chrome_driver_path = Constants.WebDriverUrl;
        System.setProperty("webdriver.chrome.driver", chrome_driver_path);
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofMillis(10000));
        driver.get("http://52.152.189.35:8080/");

        WebElement uname_inp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"input28\"]")));
        uname_inp.sendKeys("lakshmiprasanna.k@tigeranalytics.com");

        WebElement password_inp = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"input36\"]")));
        password_inp.sendKeys("Prasanna@18");

        WebElement login_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"form20\"]/div[2]/input")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", login_btn);

        WebElement reg_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/header/app-header/div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-icon")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reg_selection_element);

        WebElement reg_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option[1]/span")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", reg_option_element);

        WebElement adhoc_project_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-get-started/div/div/div/div/div[4]/button[2]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", adhoc_project_btn);

        WebElement mat_selection_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"mat-select-2\"]")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mat_selection_element);

        WebElement mat_option_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"mat-option-9\"]/span")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mat_option_element);

        WebElement aw_upload_element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"fileDropRef\"]")));
        String file_path_1 = "D:\\Sele_script\\Multiuser Testing Data\\US Bev Primary Nitro Pepsi\\4738203_42332_PEP_Nitro_Can_16oz_Vanilla_vb_OL.pdf";
        File file_1 = new File(file_path_1);
        aw_upload_element.sendKeys(file_1.getAbsolutePath());

        try {
            WebElement upload_page_next = new WebDriverWait(driver,Duration.ofSeconds(70) ).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-upload-page/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", upload_page_next);
        } catch (Exception e) {
            System.out.println("##### Error in UploadPage");
            System.out.println(e);
        }

        try {
            WebElement verify_page_next = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-verify/div/div/div/div[1]/app-side-button-panel/div/div[1]/div[2]/div[1]/button")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", verify_page_next);
        } catch (Exception e) {
            System.out.println("##### Error in VerifyPage");
            System.out.println(e);
        }

        try {
            WebElement result_page_save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/app-root/main/app-results/div/div/div[1]/app-side-button-panel/div/div/div[2]/div[1]/button")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", result_page_save);
        } catch (Exception e) {
            System.out.println("##### Error in ResultsPage");
            System.out.println(e);
        }

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        String ArtworkID = (String)((JavascriptExecutor) driver).executeScript("return window.sessionStorage.getItem('projectId');");

        driver.quit();
    }
}
