package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	 public HomePage(WebDriver driver) {
	        super(driver); // initializes driver and PageFactory
	    }
	
	@FindBy(css="a[href=\"/login\"]")
	WebElement btnLoginPage;
	
	public boolean isHomePageLoaded() {
	    try {
	        // Example: check if a known element like logo or header is displayed
	        return btnLoginPage.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	}

	public void clickLoginPage() {
		waitForClickable(btnLoginPage).click();
	}

}
