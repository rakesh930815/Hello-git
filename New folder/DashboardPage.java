package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
	
	 public DashboardPage(WebDriver driver) {
	        super(driver); 
	    }

	@FindBy(xpath="//span[contains(text(), 'OPD Registration')]")
	WebElement OPDRegistration;
	
	public boolean isOPDRegistrationPageDisplayed() {
		try {
			return OPDRegistration.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void clickOPDRegistration() {
		scrollToElement(OPDRegistration);

	    try {
	    	waitForClickable(OPDRegistration).click();
	    } catch (Exception e) {
	        // fallback JS click if normal click fails
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", OPDRegistration);
	    }
	}

}
