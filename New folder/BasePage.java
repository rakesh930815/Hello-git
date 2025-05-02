package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;

	 public BasePage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        // Set default timeout (e.g., 10 seconds)
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }
	 
	// Wait for an element to be visible
	    protected WebElement waitForVisibility(WebElement element) {
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }

	    // Wait for an element to be clickable
	    protected WebElement waitForClickable(WebElement element) {
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

	 // Wait until a WebElement is present and visible
	    protected WebElement waitForPresence(WebElement element) {
	        return wait.until(ExpectedConditions.refreshed(
	                ExpectedConditions.visibilityOf(element)
	        ));
	    }

	 public void scrollToElement(WebElement element) {
		    try {
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
		    } catch (Exception e) {
		        System.out.println("Scroll failed: " + e.getMessage());
		    }
		}

}
