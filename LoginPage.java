package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
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
		txtUserName.sendKeys(uname);
	}
	
	public void setUserPassword(String upass)
	{
		txtUserPassword.sendKeys(upass);
	}
	
	public void setCaptcha(String captcha)
	{
		txtCaptcha.sendKeys(captcha);
	}
	
	public void clickLoginBtn()
	{
		loginBtn.click();
	}
	
	public String checkLoginFailPop()
	{
		return flpvmsg.getText();
	}
	
	public void clickLoginFailPopYes()
	{
		flpvConfirm.click();
	}
	
	public String loginConfirmation()
	{
		return loginConfirmed.getText();
	}

}
