package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver); // initializes driver and PageFactory
	}

	@FindBy(id="mat-input-0")
	WebElement txtUserName;

	@FindBy(id="mat-input-1")
	WebElement txtUserPassword;

	@FindBy(css="mat-form-field input[formcontrolname=\"captchaValue\"]")
	WebElement txtCaptcha;

	@FindBy(css="button.btn.btn-primary-b")
	WebElement loginBtn;

	@FindBy(css="h2#swal2-title")
	WebElement flpvmsg;

	@FindBy(css="button.swal2-confirm")
	WebElement flpvConfirm;

	@FindBy(css="span.text[style=\"color: rgb(218, 4, 77);\"]")
	WebElement loginConfirmed;

	public void setUserName(String uname)
	{
		waitForVisibility(txtUserName).sendKeys(uname);
	}

	public void setUserPassword(String upass)
	{
		waitForVisibility(txtUserPassword).sendKeys(upass);
	}

	public void setCaptcha(String captcha)
	{
		waitForVisibility(txtCaptcha).sendKeys(captcha);
	}

	public void clickLoginBtn()
	{
		waitForClickable(loginBtn).click();
	}

	public String getLoginFailurePopupMessage()
	{
		return waitForVisibility(flpvmsg).getText();
	}

	public void clickLoginFailPopYes()
	{
		waitForClickable(flpvConfirm).click();
	}

	public String getLoginConfirmationMessage()
	{
		return waitForVisibility(loginConfirmed).getText();
	}


	// login method 
	public void login(String username, String password, String captcha) {
		setUserName(username);
		setUserPassword(password);
		setCaptcha(captcha);
		clickLoginBtn();
	}

	// Handle the "User already logged in" popup and perform a retry
	public void handleAlreadyLoggedInPopup(String captcha) {
		if (getLoginFailurePopupMessage().trim().equalsIgnoreCase("User already logged IN. Do you Want to login again")) {
			clickLoginFailPopYes();
			setCaptcha(captcha);
			clickLoginBtn();
		}
	}

	// Validate the login was successful
	public boolean isLoginSuccessful() {
		try {
			String confirmationMessage = getLoginConfirmationMessage();
			return confirmationMessage.equals("Demo Hospital"); // Assuming "Demo Hospital" is the success message
		} catch (Exception e) {
			return false; // Login failed or something went wrong
		}
	}

	// This method encapsulates the full login process with validation and popup handling
	public boolean attemptLogin(String username, String password, String captcha) {
		login(username, password, captcha);
		handleAlreadyLoggedInPopup(captcha);

		// Validate successful login
		return isLoginSuccessful();
	}
	
	public boolean isLoginPageLoaded() {
		try {
			return txtUserName.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isDashboardPageDisplayed() {
		try {
			return loginConfirmed.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}




}
