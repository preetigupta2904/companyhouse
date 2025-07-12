package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;

public class helpers {
    public static WebElement waitForElementVisible(WebDriver driver, WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

        public static void waitForSeconds(int seconds) {
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupt status
                System.out.println("Wait was interrupted: " + e.getMessage());
            }
        }

    public static LocalDate getDate(){
        LocalDate today = LocalDate.now();
        return today;
    }
}
