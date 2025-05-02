package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws InterruptedException
	{
		driver.get(baseURL);
		HomePage hp=new HomePage(driver);
		hp.clickLoginPage();
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		lp.setUserPassword(password);
		lp.setCaptcha(captcha);
		lp.clickLoginBtn();
		Thread.sleep(5000);

		
		try {
		    // Step 1: Handle "Already Logged In" Popup if it appears
		    if (lp.checkLoginFailPop().equals("User already logged IN. Do you Want to login again")) {
		        Thread.sleep(5000);
		        lp.clickLoginFailPopYes();
		        lp.setCaptcha(captcha);
		        lp.clickLoginBtn();
		    }

		    // Step 2: Check for successful login
		    if (lp.loginConfirmation().equals("Demo Hospital")) {
		        Assert.assertTrue(true);
		        System.out.println("Login Successfully !");
		        Thread.sleep(5000);
		    } else {
		        Assert.fail("Login failed - Unexpected confirmation message: " + lp.loginConfirmation());
		        driver.quit();
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		    Assert.fail("Exception during login process: " + e.getMessage());
		    driver.quit();
		}
	}

}
