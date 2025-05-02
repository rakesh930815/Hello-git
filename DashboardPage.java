package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	
	WebDriver ldriver;
	public DashboardPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	// Method to scroll to the element
    private void scrollToElement(WebElement element) {
        // JavaScriptExecutor to scroll to the element
        JavascriptExecutor js = (JavascriptExecutor) ldriver; // Assuming 'ldriver' is already defined
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

	@FindBy(xpath="//span[contains(text(), 'OPD Registration')]")
	WebElement OPDRegistration;
	
	public void clickOPDRegistration() throws InterruptedException {
		scrollToElement(OPDRegistration);
		Thread.sleep(5000);
		OPDRegistration.click();
	}

}
