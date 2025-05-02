package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage {
	
	public LogoutPage(WebDriver driver) {
        super(driver); 
    }

@FindBy(xpath="//a[.//em[contains(@class, 'fa-user')]]")
WebElement userProfileTab;

@FindBy(xpath="//a[i[contains(@class, 'fa-sign-out')]]")
WebElement logoutBtn;

@FindBy(xpath="//button[@class='btn btn-secondary' and normalize-space()='Skip Feedback']")
WebElement feedbackSkipBtn;

public void clickUserProfile() {
	waitForClickable(userProfileTab).click();
}

public void clickLogoutBtn() {
	waitForClickable(logoutBtn).click();
}

public void clickFeedbackSkipBtn() {
	waitForClickable(feedbackSkipBtn).click();
}

}
